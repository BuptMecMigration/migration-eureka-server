package com.controller;


import com.config.RpcApiConfig;
import com.dao.UserDOMapper;
import com.dataobject.UserDO;
import com.response.CommonReturnType;
import com.service.RpcCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller("test")
@RequestMapping("/test")
//对跨域请求参数进行设置保证session中的信息跨域得到读取
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class TestAPIController extends BaseController{

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    RpcCallService rpcCallService;

    @Autowired
    UserDOMapper userDOMapper;

    //节点信息
//    @RequestMapping(value = "/discoveryClient", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public CommonReturnType discoveryClientTest() {
//        ServiceInstance instance = discoveryClient.getInstances("");
//        return CommonReturnType.create("hello,client: " + instance.getHost() + ", serviceID: " + instance.getServiceId());
//    }

    //测试RPC调用
    @RequestMapping(value = "/returnTypeTest", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public CommonReturnType returnConsumer() {
        return rpcCallService.sendRpcCall("EDGE-NODE-A", RpcApiConfig.NODE_A_TEST_DISCOVERYCLIENT_API);
    }

    //测试有参数发送
    @RequestMapping(value = "/nodeParamTest", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public CommonReturnType nodeParamTest(@RequestParam(name = "name") String name) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        return rpcCallService.sendRpcCall("EDGE-NODE-A", RpcApiConfig.NODE_A_TEST_PARAM_SEND_API, map);
    }

    //测试有参数发送
    @RequestMapping(value = "/testMysql", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public CommonReturnType testMysql(@RequestParam(name = "userId")int userId) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(userId);
        if (null == userDO) {
            return CommonReturnType.create("用户不存在");
        } else {
            return CommonReturnType.create(userDO);
        }
    }
}
