package com.quartz.basic;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author someone
 */
@DisallowConcurrentExecution
public class HelloQuartz implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("hello world: " + System.currentTimeMillis());
    }
}
