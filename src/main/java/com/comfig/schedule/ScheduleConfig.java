package com.comfig.schedule;

import com.comfig.schedule.task.ScheduleTaskTest;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.SimpleTransactionFactory;

/**
 * @author zhangjun
 * 定时任务的配置
 */
//@Configuration
public class ScheduleConfig {


    @Bean
    JobDetail testTask() {
        return JobBuilder.newJob(ScheduleTaskTest.class).withIdentity("ScheduleTaskTest", "testTask").storeDurably().build();
    }

    @Bean
    Trigger triggerTask() {
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever();
        return TriggerBuilder.newTrigger().forJob(testTask()).withIdentity("ScheduleTaskTest", "triggerTask").withSchedule(simpleScheduleBuilder).build();
    }
}
