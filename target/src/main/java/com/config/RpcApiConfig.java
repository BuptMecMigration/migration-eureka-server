package com.config;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
   @author ljq，注：客户端服务端的调用接口常量（即手动配置中心，可以替换为Apollo或Spring Clouds原生组件）
 */
public class RpcApiConfig {

    //节点前缀
    private static final String PREFIX = "http://";

    //节点D相关端口:

    //节点D的测试接口API
    public static final String NODE_D_TEST_DISCOVERYCLIENT_API = "/test/discoveryClient";
    public static final String NODE_D_TEST_PARAM_SEND_API = "/test/paramTest";
    public static final String NODE_D_TEST_OBJECT_SEND_API = "/test/ObjectTest";



    public static String getRpcUrlService(String nameName, String service) {
        return getRpcAddress(nameName, service);
    }

    public static String getRpcUrlService(String nameName, String service, Map<String, String> map) {
        return addParam2Url(getRpcAddress(nameName, service), map);
    }

    private static String getRpcAddress(String nodeName, String service) {
        return PREFIX + nodeName + service;
    }

    private static String addParam2Url(String url, Map<String, String> map) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        map.entrySet().stream().forEach(o -> builder.queryParam(o.getKey(),o.getValue()));
        return builder.build().encode().toString();
    }
}
