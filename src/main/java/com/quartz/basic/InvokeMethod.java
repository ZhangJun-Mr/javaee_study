package com.quartz.basic;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author someone
 */
public class InvokeMethod {

    public static void main(String[] args) {
        try {
            //创建scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            Trigger trigger = TriggerBuilder.newTrigger()
                    //定义name/group
                    .withIdentity("trigger1", "group1")
                    //一旦加入scheduler，立即生效
                    .startNow()
                    //使用SimpleTrigger
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            //每隔一秒执行一次
                            .withIntervalInSeconds(1)
                            //一直执行，奔腾到老不停歇
                            .repeatForever())
                    .build();

            //定义一个JobDetail
            JobDetail job = JobBuilder.newJob(HelloQuartz.class)
                    .withIdentity("job1", "group1")
                    //定义属性
                    .usingJobData("name", "quartz")
                    .build();

            //加入这个调度
            scheduler.scheduleJob(job, trigger);

            //启动之
            scheduler.start();

            //运行一段时间后关闭
            Thread.sleep(10000);
            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
