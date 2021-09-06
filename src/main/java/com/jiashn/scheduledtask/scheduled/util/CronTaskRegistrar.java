package com.jiashn.scheduledtask.scheduled.util;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author: jiangjs
 * @description: 定时任务注册类，用来增加、删除定时任务
 * @date: 2021/9/6 14:55
 */
@Component
public class CronTaskRegistrar implements DisposableBean {
    private final Map<Runnable,ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

    @Autowired
    private TaskScheduler taskScheduler;

    public TaskScheduler getTaskScheduler(){
        return this.taskScheduler;
    }

    public void addCronTask(Runnable task,String cronExp){
        addCronTask(new CronTask(task,cronExp));
    }

    private void addCronTask(CronTask cronTask){
        if (Objects.nonNull(cronTask)){
            Runnable task = cronTask.getRunnable();
            if (this.scheduledTasks.containsKey(task)){
                removeCronTask(task);
            }
            this.scheduledTasks.put(task,scheduleCronTask(cronTask));
        }
    }

    public void removeCronTask(Runnable task){
        ScheduledTask scheduledTask = this.scheduledTasks.remove(task);
        if (Objects.nonNull(scheduledTask)){
            scheduledTask.cancel();
        }
    }

    public ScheduledTask scheduleCronTask(CronTask cronTask){
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.scheduledFuture = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return scheduledTask;
    }
    @Override
    public void destroy() throws Exception {
        for (ScheduledTask scheduledTask : this.scheduledTasks.values()) {
            scheduledTask.cancel();
        }
        this.scheduledTasks.clear();
    }
}
