package com.service;

import com.dataobject.MigrationToken;
import com.error.BusinessException;

/***
 * @apiNote 此接口主要用来与下层节点同步进行锁定迁移任务的方向，保证同一时刻用一用户的同一服务只能有一个迁移任务
 */
public interface MigrationService {

    boolean migrationLockHandler(MigrationToken token);

    boolean migrationUnLockHandler(MigrationToken token);

    void migrationRedisLockHandler(MigrationToken token) throws BusinessException, InterruptedException;
}
