package com.jiashn.scheduledtask.scheduled.util;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author: jiangjs
 * @description: 添加Runnable接口实现，被定时任务线程池调用，来执行指定bean里面的方法
 * @date: 2021/9/6 14:26
 */
@Data
public class SchedulingRunnable implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(SchedulingRunnable.class);

    /**
     * 类名
     */
    private String beanName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数
     */
    private String params;

    public SchedulingRunnable(String beanName,String methodName){
        this.beanName = beanName;
        this.methodName = methodName;
    }
    public SchedulingRunnable(String beanName,String methodName,String params){
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
    }
    @Override
    public void run() {
        Object bean = SpringContextUtils.getBean(beanName);
        Method method = null;
        try {
            if (StringUtils.isNotBlank(params)){
                method = bean.getClass().getDeclaredMethod(methodName,String.class);
            } else {
                method = bean.getClass().getDeclaredMethod(methodName);
            }
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotBlank(params)){
                method.invoke(bean,params);
            } else {
                method.invoke(bean);
            }
        }catch (Exception e){
            logger.error("获取方法信息报错：{}",e.getMessage());
        }
    }


}
