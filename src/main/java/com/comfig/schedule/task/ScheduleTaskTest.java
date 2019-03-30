package com.comfig.schedule.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;


/**
 * @author zhangjun
 * 定时任务测试
 */
public class ScheduleTaskTest extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toString());
    }
}
