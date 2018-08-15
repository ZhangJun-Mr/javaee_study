package com.quartz.complex;

import com.quartz.basic.HelloQuartz;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author someone
 */
@Configuration
public class QuartzConfiguration {

    private String jobDetailHi = "jobDetailHi";
    private String jobDetailHelloWorld = "jobDetailHelloWorld";
    private String jobDetailGroup = "jobDetailGroup";
    private String cronTriggerHelloWorld = "cronTriggerHelloWorld";
    private String cronTriggerHi = "cronTriggerName";
    private String cronTriggerGroup = "cronTriggerGroup";

    private Properties newProperties() {
        Properties properties = new Properties();
        properties.setProperty(StdSchedulerFactory.PROP_SCHED_INSTANCE_NAME, "MySchedule");
        properties.setProperty(StdSchedulerFactory.PROP_TABLE_PREFIX, "QRTZ_");
        properties.setProperty(StdSchedulerFactory.PROP_SCHED_SKIP_UPDATE_CHECK, "true");
        return properties;
    }

    private JobDetailFactoryBean createJobDetail(String jobDetailName1, String jobDetailGroup1, Class cls, JobDataMap jobDataMap) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setGroup(jobDetailGroup1);
        jobDetailFactoryBean.setName(jobDetailName1);
        jobDetailFactoryBean.setJobClass(cls);
        jobDetailFactoryBean.setJobDataMap(jobDataMap);
        return jobDetailFactoryBean;
    }

    private CronTriggerFactoryBean cronTrigger(JobDetailFactoryBean jobDetailFactoryBean, String cronTriggerName, String cronTriggerGroup){
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setGroup(cronTriggerGroup);
        cronTriggerFactoryBean.setName(cronTriggerName);
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("cronTriggerData", "cronTriggerDataValue");
        cronTriggerFactoryBean.setJobDataAsMap(map);
        cronTriggerFactoryBean.setCronExpression("0/5 * * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean
    JobDetailFactoryBean jobDetailFactoryHelloWorld() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobData", "jobDataValue");
        return createJobDetail(jobDetailHelloWorld, jobDetailGroup, HelloQuartz.class, jobDataMap);
    }

    @Bean
    JobDetailFactoryBean jobDetailHi() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobData", "jobDataHi");
        return createJobDetail(jobDetailHi, jobDetailGroup, HiJob.class, jobDataMap);
    }

    @Bean(name = "cronTriggerFactoryHelloWorld")
    CronTriggerFactoryBean cronTriggerFactoryHelloWorld() {
        CronTriggerFactoryBean cronTriggerFactoryBean = cronTrigger(jobDetailFactoryHelloWorld(), cronTriggerHelloWorld, cronTriggerGroup);
//        cronTriggerFactoryBean.setPriority(2);
        return cronTriggerFactoryBean;
    }
    @Primary
    @Bean(name = "cronTriggerFactoryHi")
    CronTriggerFactoryBean cronTriggerFactoryHi() {
        CronTriggerFactoryBean cronTriggerFactoryBean =  cronTrigger(jobDetailHi(), cronTriggerHi, cronTriggerGroup);
//        cronTriggerFactoryBean.setPriority(1);
        return cronTriggerFactoryBean;
    }

    @Bean
    SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, List<CronTrigger> cronTriggerList) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setStartupDelay(10);
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
//        schedulerFactoryBean.setTriggers(cronTriggerFactoryBeans.get(1).getObject(), cronTriggerFactoryBeans.get(0).getObject());
        schedulerFactoryBean.setTriggers(cronTriggerList.toArray(new CronTrigger[cronTriggerList.size()]));
        schedulerFactoryBean.setQuartzProperties(newProperties());
        return schedulerFactoryBean;
    }

    @Bean
    Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
//        JobKey jobKey = JobKey.jobKey(jobDetailHi, jobDetailGroup);
//        JobKey jobKey1 = JobKey.jobKey(jobDetailHelloWorld, jobDetailGroup);
//        JobChainingJobListener jobChainingJobListener = new JobChainingJobListener("pipe line Chain");
//        jobChainingJobListener.addJobChainLink(jobKey, jobKey1);
//        scheduler.getListenerManager().addJobListener(jobChainingJobListener);
        return scheduler;
    }



}
