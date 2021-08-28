package com.constants;

public class ChainConstants {

    //用来区分本地缓存读取链信息还是去远端更新链信息
    public static final String CHAIN_CALL_BY_RPC = "rpc";

    public static final String CHAIN_CALL_BY_LOCAL = "local";

    //服务压力过大时拒绝较长链信息转移
    public static final int CHAIN_SERVICE_NUM_LIMIT = 20;
}
