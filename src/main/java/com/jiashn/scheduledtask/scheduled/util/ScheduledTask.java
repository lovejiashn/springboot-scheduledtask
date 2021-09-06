package com.jiashn.scheduledtask.scheduled.util;

import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

/**
 * @author: jiangjs
 * @description: 添加ScheduledFuture的包装类,ScheduledFuture是ScheduledExecutorService定时任务线程池的执行结果
 * @date: 2021/9/6 14:23
 */
public final class ScheduledTask {

    volatile ScheduledFuture<?> scheduledFuture;

    /**
     * 取消定时任务
     */
    public void cancel(){
        ScheduledFuture<?> future = this.scheduledFuture;
        if (Objects.nonNull(future)){
            future.cancel(true);
        }
    }
}
