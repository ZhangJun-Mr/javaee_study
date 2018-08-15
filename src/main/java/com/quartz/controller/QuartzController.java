package com.quartz.controller;

import com.quartz.complex.HiThrJob;
import org.quartz.*;
import org.quartz.listeners.BroadcastJobListener;
import org.quartz.listeners.JobChainingJobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author someone
 */
@RestController
public class QuartzController {

//    @Autowired
    Scheduler scheduler;


    JobDetailFactoryBean jobDetailFactoryBean;


    @RequestMapping("/")
    void quartz() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob()
                .withIdentity("thrJob", "jobDetailGroup")
                .ofType(HiThrJob.class)
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("thrTrigger", "cronTriggerGroup")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        JobKey jobKeyNew = new JobKey("thrJob", "jobDetailGroup");
        JobKey jobKeyHi = new JobKey("jobDetailHi", "jobDetailGroup");
        JobChainingJobListener jobChainingJobListener = new JobChainingJobListener("new job listener");
        jobChainingJobListener.addJobChainLink(jobKeyHi, jobKeyNew);
        scheduler.getListenerManager().addJobListener(jobChainingJobListener);
    }

    @RequestMapping("/bean")
    void quartzBean() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob()
                .withIdentity("thrJob", "jobDetailGroup")
                .ofType(HiThrJob.class)
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("thrTrigger", "cronTriggerGroup")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        JobKey jobKeyNew = new JobKey("thrJob", "jobDetailGroup");
        JobKey jobKeyHi = new JobKey("jobDetailHi", "jobDetailGroup");
        JobChainingJobListener jobChainingJobListener = new JobChainingJobListener("new job listener");
        jobChainingJobListener.addJobChainLink(jobKeyHi, jobKeyNew);
        BroadcastJobListener broadcastJobListener = new BroadcastJobListener("job broadcast");
        broadcastJobListener.addListener(jobChainingJobListener);
        scheduler.getListenerManager().addJobListener(broadcastJobListener);
    }
}
