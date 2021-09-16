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
    /**
     *  存储线程对应的ScheduledFuture，即线程池服务ScheduledExecutorService执行结果
     */
    private final Map<Runnable,ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

    @Autowired
    private TaskScheduler taskScheduler;

    public void addCronTask(Runnable task,String cronExp){
        //new CronTask ：创建任务，参数：定时任务类创建的线程，定时任务cron表达式
        addCronTask(new CronTask(task,cronExp));
    }

    private void addCronTask(CronTask cronTask){
        if (Objects.nonNull(cronTask)){
            Runnable task = cronTask.getRunnable();
            //如果线程的map中存在任务，则删除
            if (this.scheduledTasks.containsKey(task)){
                removeCronTask(task);
            }
            //将任务添加入map中，其作用是便于管理
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
        //创建定时任务
        ScheduledTask scheduledTask = new ScheduledTask();
        //将定时任务线程与触发器加入schedule中
        scheduledTask.scheduledFuture = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return scheduledTask;
    }
    @Override
    public void destroy() throws Exception {
        //注销，则将map中所有的定时任务cancel掉
        for (ScheduledTask scheduledTask : this.scheduledTasks.values()) {
            scheduledTask.cancel();
        }
        this.scheduledTasks.clear();
    }
}
