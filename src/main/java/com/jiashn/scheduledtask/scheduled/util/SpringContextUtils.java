package com.jiashn.scheduledtask.scheduled.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: jiangjs
 * @description:
 * @date: 2021/9/6 14:38
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtils.context = applicationContext;
    }

    public static Object getBean(String name){
        return context.getBean(name);
    }
}
