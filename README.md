# migration-eureka-server
服务中心

## 设定

升级版边缘计算服务迁移系统上层服务核心控制模块，主要使用技术为Springboot和Springcloud组件

### 关于系统

系统主要包括三个部分：
- A: migration-eureka-server
- B: migration-eureka-client
- C: migration-register-centor

- *C的功能主要是维持多节点间的RPC调用，作为服务发现的提供者，因此一般直接与服务中心A同机部署即可。*
- *A的主要功能是作为系统的核心控制模块，当迁移发起时在最基础的地方进行服务调控和许可运行，并执行用户接入注册的落表环节。*
- *B的主要功能是作为系统的用户接入代理，故需要执行RPC请求访问并与服务中心进行沟通，并将用户的结果下发。*

### 功能修改

本系统是本人毕业设计所做工作的简化版本，为了便于部署、维护和实现服务迁移上层系统的基本功能，保留之前的回退开发版本并去除了以下几项功能：
- **以RocketMQ为主要设计的消息队列发送机制**。经过测试RocketMQ在多机部署过程中打jar包有管理混乱的问题，在新环境下启动常常导致部署异常，未来得及定位解决故整体去除，计划任务模块由原来的异步处理改为rpc同步发送模式，并去除日志在中心服务节点的处理逻辑，不影响主要逻辑；
- **RPC调用由重写RestTemplate模式改为配置中心模式**。主要是考虑到常规生产环境下都是注册中心分离形式存在的，故去除重写发送的封装而改为直接读取返回的形式。但其部署和新增接口没有自动发布机制，毕竟是实验室项目，在多节点部署上只能因陋就简来改良，不影响主要逻辑；
- **所有访问接口的AOP日志封装**。最初采取的逻辑是设计统一的重写模式接入，但是AOP的封装和连接需要重新部署Redis缓存连入系统，考虑到增加部署的复杂性和中心模块的日志写入内容，且对于调试的实际帮助并不很大，因此去除该部分内容，不影响主要逻辑；

### 其他

- 系统基本执行逻辑和完成的任务可见本人毕业设计论文所涉及的部分，同时因未来得及与下层系统进行部分联调，论文中进行压力测试的部分采取的实验室之前的设计下衍生demo的测试结果，具体上下层系统再加上mini-net的联调工作需要重新测试，此处只预留了部分接口；
- SpringBoot部分的异常处理采用的是全局模式的处理，故没有显示的异常处理，因此排查故障时可以直接控制台看到异常，需要写入日志时需要进行手动设置路径，部署不便故没有重写；
- 用户数据的MySQL信息，本系统就不导出信息demo了，接手时重新新建即可（表结构我会导出）；

## 部署

### 部署顺序

采用C-A-B的顺序对服务进行部署。

### 部署方式

- 首先，采用mvn clean package命令对服务进行打包，产生的jar包位于/targer目录下，jar包版本和名称与pom设置一致；
- 其次，按照表结构使用mysql管道命令导入表结构，按照application设置的连接路径构建远程连接的数据库配置；
- 然后，使用scp命令传输对应jar包到服务器上并使用nohup进行部署，在注册中心C的访问页面可以看到多节点都接入；
- 最后，在用户端侧B即可访问接口进行用户侧服务；


### 部署脚本

部署前先使用chmod -R 777 * 命令修改jar包和deploy.sh的属性。

- 在项目目录下编写deploy.sh文件，安装自己的路径编写
```sh
nohup java -Xms400m -Xmx400m -XX:NewSize=200m -XX:MaxNewSize=200m
//最大堆栈，最小堆栈，新生代大小 最大新生代大小
-jar seckillmall.jar --spring.config.addition-location=你自己的路径/项目名称/application.properties
```
- 对sh文件chmod  -R 777 *
- 修改application.properties文件的信息，使其符合现有几个服务器节点配置情况
- 使用./deploy.sh执行，并将输出追加到nohup.out里，可以使用以下命令查看
```shell
tail -200f nohup.out
```

### 注意事项

- 云服务器的对应防火墙端口需要打开否则无法访问
