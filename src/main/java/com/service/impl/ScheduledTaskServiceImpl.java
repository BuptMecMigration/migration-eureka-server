package com.service.impl;

import com.dataobject.ScheduledJob;
import com.response.CommonReturnType;
import com.service.ScheduledTaskService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskServiceImpl implements ScheduledTaskService {

    @Override
    @Async
    public CommonReturnType scheduledJobHandler(ScheduledJob scheduledJob) {

        //多线程处理计划任务发布


        return CommonReturnType.create("计划任务发布成功");
    }
}
