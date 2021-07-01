package com.service.impl;

import com.dataobject.MigrationToken;
import com.error.BusinessException;
import com.lock.MigrationLock;
import com.lock.RedisLock;
import com.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MigrationServiceImpl implements MigrationService {

    //此处场景主要是多个springboot服务器访问同一个节点（即服务中心）的redis，因此用redis做一个分布式锁结构
    //现已经修改
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MigrationLock migrationLock;

    @Override
    public boolean migrationLockHandler(MigrationToken token) {

        //验证token合法等操作，去除

        //查询，若有锁则无法提交迁移任务
        if (!migrationLock.lock(token.getTokenCode())) {
            return false;
        }

        //map内上锁，可以处理迁移任务
        migrationLock.lock(token.getTokenCode());

        return true;
    }

    @Override
    public boolean migrationUnLockHandler(MigrationToken token) {

        //查询，若无锁则直接成功
        if (!migrationLock.lock(token.getTokenCode())) {
            return true;
        }
        migrationLock.unlock(token.getTokenCode());
        return true;
    }

    @Override
    public void migrationRedisLockHandler(MigrationToken token) throws BusinessException, InterruptedException {
        //上锁代码使用分布式锁进行
        RedisLock lock = new RedisLock(redisTemplate, token.getTokenCode(), 10000, 20000);

        try {
            if(lock.lock()) {
                //此前采取redis分布式锁，现已修改，保留接口
            }
        } finally {
        //为了让分布式锁的迁移算法的鲁棒性，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起。
        //操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。
        lock.unlock();
    }
    }
}
