package com.service.impl;

import com.config.RpcApiConfig;
import com.dataobject.UserDO;
import com.response.CommonReturnType;
import com.response.RPCReturnType;
import com.service.RpcCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RpcCallServiceImpl implements RpcCallService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public CommonReturnType sendRpcCall(String nodeName, String apiName) {
        RPCReturnType result = restTemplate.getForEntity(RpcApiConfig.getRpcUrlService(nodeName, apiName), RPCReturnType.class).getBody();
        return CommonReturnType.create(result.getData());
    }

    @Override
    public CommonReturnType sendRpcCall(String nodeName, String apiName, Map<String, String> map) {
        RPCReturnType result = restTemplate.getForEntity(RpcApiConfig.getRpcUrlService(nodeName, apiName, map), RPCReturnType.class).getBody();
        return CommonReturnType.create(result.getData());
    }

    @Override
    public CommonReturnType sendJsonCall(String nodeName, String apiName, String json) {
        RPCReturnType result = restTemplate.postForObject(RpcApiConfig.getRpcUrlService(nodeName, apiName), json, RPCReturnType.class);
        return CommonReturnType.create(result.getData());
    }
}
