package com.error;

public enum SystemError implements CommonError {

    //通用错误类型100x，解决系统问题
    SYSTEM_ERROR(1001, "系统运行错误"),
    //未知错误
    UNKNOWN_ERROR(1002, "系统未知错误"),

    //200x开头表示为系统中间件错误
    RPC_ERROR(2001, "RPC发送错误"),

    //300x开头表示系统业务错误
    LOG_ADD_FAIL(3001, "日志打点错误"),

    //400x开头表示redis分布式锁错误
    REDIS_LOCK_GET_ERROR(4001, "分布式锁获取错误"),
    REDIS_LOCK_SETNX_ERROR(4002, "分布式锁设置错误")
    ;

    SystemError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;
    private String errMsg;

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
