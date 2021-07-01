package com.service.impl;

import com.config.BlackListConfig;
import com.config.SystemConfig;
import com.dataobject.MigrationToken;
import com.response.CommonReturnType;
import com.response.RPCReturnType;
import com.service.CacheService;
import com.service.MigrationPermissionService;
import com.service.NodeCallerService;
import com.service.model.MigrationTaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MigrationPermissionServiceImpl implements MigrationPermissionService {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private NodeCallerService nodeCallerService;

    @Override
    public CommonReturnType getMigrationPermission4Client(MigrationTaskModel migrationTaskModel) {

        //黑名单检测，如果在黑名单中则直接拒绝迁移
        Integer count = (Integer) cacheService.getFromCommonCache("userId_" + migrationTaskModel.getUserId());

        if (count >= BlackListConfig.BLACK_LIST_LIMIT_NUM) {
            return CommonReturnType.create(false, "用户短时间发起迁移次数过多");
        }

        //节点探活处理
        if (! nodeCallerService.nodeCallerManager(migrationTaskModel.getDestinationIP(), migrationTaskModel.getDestinationPort())) {
            return CommonReturnType.create(false, "目的节点失活");
        }

        //收集计算信息Map
        HashMap<String, Integer> cntMap = new HashMap<>();

        Integer nodePressure = nodeCallerService.nodePressureCaller(migrationTaskModel.getDestinationIP(),
                migrationTaskModel.getDestinationPort());
        cntMap.put("nodePressure", nodePressure);
        cntMap.put("task", migrationTaskModel.getTaskId());
        cntMap.put("userLevel", migrationTaskModel.getUserLevel());
        cntMap.put("userMigrationTimes", count);

        //计算K值获取Token
        MigrationToken token = coreManagerCompute(migrationTaskModel, cntMap);

        if (null == token || null == token.getStatus()|| "fail".equals(token.getStatus())) {
            return CommonReturnType.create(false, "用户K值计算错误，或令牌下发有误");
        }

        if (token.getKval() < SystemConfig.SYSTEM_K_VALUE) {
            return CommonReturnType.create(false, "用户K值小于系统设置，拒绝迁移");
        }

        return CommonReturnType.create(token);
    }

    private MigrationToken coreManagerCompute(MigrationTaskModel migrationTaskModel, Map<String, Integer> cntMap) {

        if (null == cntMap || cntMap.size() == 0) {
            return MigrationToken.fail();
        }

        //核心Token计算算法
        Integer KVal = 0;
        for (Map.Entry<String, Integer> entry : cntMap.entrySet()) {
            KVal += SystemConfig.computeMap.get(entry.getKey() + "Weight") * entry.getValue();
        }

        MigrationToken token = new MigrationToken();
        token.setUserId(migrationTaskModel.getUserId());
        token.setTaskId(migrationTaskModel.getTaskId());
        token.setKval(KVal);
        token.setTokenCode(UUID.randomUUID().toString());
        token.setStatus("success");

        return token;
    }
}
