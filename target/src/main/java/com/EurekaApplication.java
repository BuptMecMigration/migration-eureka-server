package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 服务端启动App
 *
 */

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com"})
@MapperScan("com.dao")
public class EurekaApplication
{
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        // 客户端负载均衡
        return new RestTemplate();
    }

    public static void main( String[] args )
    {
        SpringApplication.run(EurekaApplication.class, args);
        System.out.println( "服务迁移系统多节点服务中心启动!" );
    }
}
