package com.service;

import com.dataobject.ScheduledJob;
import com.response.CommonReturnType;

/***
 * @apiNote 此接口主要用来将计划任务下发到不同节点上
 */
public interface ScheduledTaskService {

    //计划任务处理接口
    CommonReturnType scheduledJobHandler(ScheduledJob scheduledJob);
}
