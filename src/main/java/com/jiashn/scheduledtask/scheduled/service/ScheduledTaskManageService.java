package com.jiashn.scheduledtask.scheduled.service;

import com.jiashn.scheduledtask.scheduled.entity.SysSchedulerTask;
import com.jiashn.scheduledtask.utils.ResultJson;

/**
 * @author: jiangjs
 * @description:
 * @date: 2021/9/6 16:56
 */
public interface ScheduledTaskManageService {

    /**
     * 添加定时任务信息
     * @param schedulerTask 定时任务实体
     * @return 创建结果
     */
    ResultJson<?> createScheduledTask(SysSchedulerTask schedulerTask);
    /**
     * 修改定时任务信息
     * @param schedulerTask 定时任务实体
     * @return 更新结果
     */
    ResultJson<?> editScheduledTask(SysSchedulerTask schedulerTask);
    /**
     * 删除定时任务信息
     * @param id 任务Id
     * @return 删除结果
     */
    ResultJson<?> deleteScheduledTask(int id);

    /**
     * 根据任务Id查找任务信息
     * @param id 任务Id
     * @return 查询结果
     */
    ResultJson<SysSchedulerTask> findScheduledTask(int id);
}
