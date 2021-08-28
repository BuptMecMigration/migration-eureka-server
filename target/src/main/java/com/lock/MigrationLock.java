package com.lock;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MigrationLock {

    private ConcurrentHashMap<String, DateTime> migrationLock = null;

    @PostConstruct
    private void initLockMap() {
        migrationLock = new ConcurrentHashMap<>();
    }

    //上锁
    public boolean lock(String tokenID) {
        if (null == migrationLock.get(tokenID)) {
            migrationLock.put(tokenID, DateTime.now());
            return true;
        } else {
            return false;
        }
    }

    //去除锁定
    public void unlock(String tokenID) {
        migrationLock.remove(tokenID);
    }

}
