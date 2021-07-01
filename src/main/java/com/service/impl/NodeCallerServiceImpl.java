package com.service.impl;

import com.service.NodeCallerService;
import org.springframework.stereotype.Service;

@Service
public class NodeCallerServiceImpl implements NodeCallerService {

    @Override
    public boolean nodeCallerManager(String nodeAddr, String nodePort) {

        //对节点探活处理

        return true;
    }

    @Override
    public Integer nodePressureCaller(String nodeAddr, String nodePort) {

        return null;
    }
}
