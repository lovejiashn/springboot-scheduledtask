package com.jiashn.scheduledtask.scheduled.service.impl;

import com.jiashn.scheduledtask.scheduled.entity.SysSchedulerTask;
import com.jiashn.scheduledtask.scheduled.mapper.ScheduledTaskManageMapper;
import com.jiashn.scheduledtask.scheduled.service.ScheduledTaskManageService;
import com.jiashn.scheduledtask.scheduled.util.CronTaskRegistrar;
import com.jiashn.scheduledtask.scheduled.util.SchedulingRunnable;
import com.jiashn.scheduledtask.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: jiangjs
 * @description:
 * @date: 2021/9/6 16:56
 */
@Service
public class ScheduledTaskManageServiceImpl implements ScheduledTaskManageService {

    @Autowired
    private ScheduledTaskManageMapper taskManageMapper;
    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;
    @Override
    public ResultJson<?> createScheduledTask(SysSchedulerTask schedulerTask) {
        int count = taskManageMapper.insert(schedulerTask);
        if (count > 0){
            if (schedulerTask.getTaskStatus() == 1){
                //创建定时任务方法线程
                SchedulingRunnable runnable = new SchedulingRunnable(schedulerTask.getBeanName(), schedulerTask.getMethodName(), schedulerTask.getParams());
                //将线程加入定时任务map中
                cronTaskRegistrar.addCronTask(runnable,schedulerTask.getCronExp());
            }
        }
        return ResultJson.success();
    }

    @Override
    public ResultJson<?> editScheduledTask(SysSchedulerTask schedulerTask) {
        if (Objects.isNull(schedulerTask.getId()) || schedulerTask.getId() <= 0){
            return ResultJson.error(404,"请传递正确的任务id");
        }
        SysSchedulerTask sysSchedulerTask = taskManageMapper.selectById(schedulerTask.getId());
        if (Objects.isNull(sysSchedulerTask)){
            return ResultJson.error(404,"未找到该任务");
        }
        int count = taskManageMapper.updateById(schedulerTask);
        //需要删除已存在的定时任务信息
        if (sysSchedulerTask.getTaskStatus() == 1){
            SchedulingRunnable runnable = new SchedulingRunnable(sysSchedulerTask.getBeanName(),sysSchedulerTask.getMethodName(),sysSchedulerTask.getParams());
            cronTaskRegistrar.removeCronTask(runnable);
        }
        //更新数据库数据成功，并且任务的状态为正常状态，则需要将该任务添加
        if (count > 0 && schedulerTask.getTaskStatus() == 1){
            SchedulingRunnable runnable = new SchedulingRunnable(schedulerTask.getBeanName(),schedulerTask.getMethodName(),schedulerTask.getParams());
            cronTaskRegistrar.addCronTask(runnable,schedulerTask.getCronExp());
        }
        return ResultJson.success();
    }

    @Override
    public ResultJson<?> deleteScheduledTask(int id) {
        SysSchedulerTask sysSchedulerTask = taskManageMapper.selectById(id);
        if (Objects.isNull(sysSchedulerTask)){
            return ResultJson.error(404,"当前任务未找到");
        }
        taskManageMapper.deleteById(id);
        if (sysSchedulerTask.getTaskStatus() == 1){
            SchedulingRunnable runnable = new SchedulingRunnable(sysSchedulerTask.getBeanName(),sysSchedulerTask.getMethodName(),sysSchedulerTask.getParams());
            cronTaskRegistrar.removeCronTask(runnable);
        }
        return ResultJson.success();
    }

    @Override
    public ResultJson<SysSchedulerTask> findScheduledTask(int id) {
        SysSchedulerTask sysSchedulerTask = taskManageMapper.selectById(id);
        return ResultJson.success(sysSchedulerTask);
    }
}
