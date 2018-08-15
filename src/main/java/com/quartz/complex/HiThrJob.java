package com.quartz.complex;

import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author someone
 */
@DisallowConcurrentExecution
public class HiThrJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        System.out.println("HiThrJob: ");
    }
}
