package com.quartz.complex;

import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author someone
 */
@DisallowConcurrentExecution
public class HiJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        String jobString = (String) jobDataMap.get("jobData");
        System.out.println("Hijob: " +jobString);
    }
}
