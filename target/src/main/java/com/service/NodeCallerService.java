package com.service;

public interface NodeCallerService {

    boolean nodeCallerManager(String nodeAddr, String nodePort);

    Integer nodePressureCaller(String nodeAddr, String nodePort);
}
