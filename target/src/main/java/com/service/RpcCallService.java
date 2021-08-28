package com.service;

import com.dataobject.UserDO;
import com.response.CommonReturnType;

import java.util.Map;

/***
 * @apiNote 此接口主要用来各节点间发送RPC请求
 */
public interface RpcCallService {

    CommonReturnType sendRpcCall(String nodeName, String apiName);

    CommonReturnType sendRpcCall(String nodeName, String apiName, Map<String, String> map);

    CommonReturnType sendJsonCall(String nodeName, String apiName, String json);
}
