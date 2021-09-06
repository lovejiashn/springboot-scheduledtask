package com.jiashn.scheduledtask.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author: jiangjs
 * @description: 数据库连接池配置类
 * @date: 2021/9/6 11:42
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.druid")
@Data
public class DruidConfig {
    private String url;
    private String userName;
    private String passWord;
    private String driverClassName;
    private Integer maxActive;
    private long maxWait;
    private Integer initialSize;

    @Bean
    public DataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(passWord);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setInitialSize(initialSize);
        return druidDataSource;
    }
}
