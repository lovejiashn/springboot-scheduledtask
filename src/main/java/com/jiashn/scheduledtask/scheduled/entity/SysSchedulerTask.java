package com.jiashn.scheduledtask.scheduled.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;

/**
 * sys_scheduler_task
 * @author 
 */
@Data
@TableName("sys_scheduler_task")
public class SysSchedulerTask implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 类名
     */
    @NotBlank(message = "类名不能为空")
    private String beanName;

    /**
     * 方法名称
     */
    @NotBlank(message = "方法名称不能为空")
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * cron表达式
     */
    @NotBlank(message = "cron表达式不能为空")
    private String cronExp;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态（1 正常，0 暂停）
     */
    private Integer taskStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}