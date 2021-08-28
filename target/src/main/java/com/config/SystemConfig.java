package com.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 @author ljq，注：系统全局设置
 */
public class SystemConfig {

    // 系统迁移K值，非常重要！
    public static final int SYSTEM_K_VALUE = 20;

    // 核心控制函数参数列表
    public static final HashMap<String, Integer> computeMap = new HashMap<String, Integer>() {{
        computeMap.put("nodePressureWeight", 2);
        computeMap.put("taskWeight", 5);
        computeMap.put("userLevelWeight", 4);
        computeMap.put("userMigrationTimesWeight", 2);
    }};
}
