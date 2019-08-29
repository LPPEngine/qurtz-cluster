package com.qe.quatz.jobdefine;

import com.qe.quatz.JobConstant;
import com.qe.quatz.Jobhelper.JobPropertiesHelper;
import com.qe.quatz.job.CookingJob;
import com.qe.quatz.job.MyJob;
import com.qe.quatz.job.SingJob;
import com.qe.quatz.job.TriggerSpringBatchJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author <a href="mailto:v-ksong@expedia.com">ksong</a>
 */
@Configuration
public class JobConfiguration {
    @Autowired
    JobPropertiesHelper jobPropertiesHelper;

    @Bean("jobScheduler")
    public Scheduler scheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        return scheduler;
    }

    @Bean("testJob")
    public JobDetail testJob(@Autowired Scheduler jobScheduler){
        JobDetail jobDetail = null;
        try {
            // job 唯一标识 test.test-1
            JobKey jobKey = new JobKey("test3", "test-3");
            //if the job already exit in database
            if(jobScheduler.checkExists(jobKey)){
                return jobScheduler.getJobDetail(jobKey);
            }else {
                jobDetail = JobBuilder.newJob(MyJob.class)
                        .withIdentity(jobKey)
                        .storeDurably()
                        .requestRecovery()
                        .build();
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity("test3", "test-3")
                        // 延迟一秒执行
                        .startAt(new Date(System.currentTimeMillis() + 1000))
                        // 每隔一秒执行 并一直重复
                        .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                        .build();
                jobScheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobDetail;
    }
//    @Bean("cookingJob")
//    public JobDetail cookingJob(@Autowired Scheduler jobScheduler) throws SchedulerException {
//
//        JobKey cookingJobKey = new JobKey("cookingJob","cookingJobGroup");
//        if(jobScheduler.checkExists(cookingJobKey)){
//            return jobScheduler.getJobDetail(cookingJobKey)
//                    .getJobBuilder()
//                    .usingJobData(JobConstant.COOK,jobPropertiesHelper.getCook())
//                    .usingJobData(JobConstant.FOOD,jobPropertiesHelper.getFood())
//                    .build();
//        } else {
//            JobDetail cookingJobDetail = JobBuilder.newJob(CookingJob.class)
//                    .withIdentity(cookingJobKey)
//                    .usingJobData("cook","Kenny")
//                    .usingJobData("food","fish")
//                    .storeDurably()
//                    .requestRecovery()
//                    .build();
//            Trigger cookingTrigger = TriggerBuilder.newTrigger()
//                    .withIdentity("cookingTrigger", "cookingTriggerGroup")
//                    .startAt(new Date(System.currentTimeMillis() + 1000))
//                    .withSchedule(CronScheduleBuilder.cronSchedule("0/8 * * * * ?"))
//                    .build();
//            jobScheduler.scheduleJob(cookingJobDetail, cookingTrigger);
//            return cookingJobDetail;
//        }
//    }

    @Bean("cookingTestJob")
    public JobDetail cookingTestJob(@Autowired Scheduler jobScheduler) throws SchedulerException {

        JobKey cookingTestJobKey = new JobKey("cookingTestJob","cookingJobGroup");
        if(jobScheduler.checkExists(cookingTestJobKey)){
            return jobScheduler.getJobDetail(cookingTestJobKey);
        } else {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put(JobConstant.COOK,jobPropertiesHelper.getCook());
            jobDataMap.put(JobConstant.FOOD,jobPropertiesHelper.getFood());
            jobDataMap.put("jobPropertiesHelper",jobPropertiesHelper);
            JobDetail cookingJobDetail = JobBuilder.newJob(CookingJob.class)
                    .withIdentity(cookingTestJobKey)
                    .setJobData(jobDataMap)
                    .storeDurably()
                    .requestRecovery()
                    .build();
            Trigger cookingTrigger = TriggerBuilder.newTrigger()
                    .withIdentity("cookingTestTrigger", "cookingTriggerGroup")
                    .startAt(new Date(System.currentTimeMillis() + 1000))
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                    .build();
            jobScheduler.scheduleJob(cookingJobDetail, cookingTrigger);
            return cookingJobDetail;
        }
    }

    @Bean("triggerSpringBatchJob")
    public JobDetail triggerSpringBatchJob(@Autowired Scheduler jobScheduler) throws SchedulerException {
        JobKey triggerSpringBatchJobKey = new JobKey("triggerSpringBatchJob","triggerSpringBatchJobGroup");
        if(jobScheduler.checkExists(triggerSpringBatchJobKey)){
            return jobScheduler.getJobDetail(triggerSpringBatchJobKey);
        }else {
            JobDetail triggerSpringBatchJobDetail = JobBuilder.newJob(TriggerSpringBatchJob.class)
                    .withIdentity(triggerSpringBatchJobKey)
                    .storeDurably()
                    .requestRecovery()
                    .build();
            Trigger triggerSpringBatchJobTrigger = TriggerBuilder.newTrigger()
                    .withIdentity("triggerSpringBatchJobTrigger","triggerSpringBatchJobTrigger")
                    .startAt(new Date(System.currentTimeMillis() + 1000))
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                    .build();
            jobScheduler.scheduleJob(triggerSpringBatchJobDetail,triggerSpringBatchJobTrigger);
            return triggerSpringBatchJobDetail;
        }
    }

    @Bean("eatJob")
    public JobDetail eatJob(@Autowired Scheduler jobScheduler) throws SchedulerException {
        JobKey singJobKey = new JobKey("eatJob","eatJobGroup");
        try {
            if(jobScheduler.checkExists(singJobKey)){
                return jobScheduler.getJobDetail(singJobKey);
            }else {
                JobDataMap jobDataMap = new JobDataMap();
                jobDataMap.put(JobConstant.COOK,jobPropertiesHelper.getSinger());
                jobDataMap.put("jobPropertiesHelper",jobPropertiesHelper);
                JobDetail eatJob = JobBuilder.newJob(SingJob.class)
                        .withIdentity(singJobKey)
                        .storeDurably()
                        .setJobData(jobDataMap)
                        .requestRecovery()
//                        .usingJobData("jobPropertiesHelper",jobPropertiesHelper.getClass())
                        .build();
                Trigger singJobTrigger = TriggerBuilder.newTrigger()
                        .withIdentity("eatJobTrigger","eatJobTriggerGroup")
                        .startAt(new Date(System.currentTimeMillis() + 1000))
                        .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                        .build();
                jobScheduler.scheduleJob(eatJob,singJobTrigger);
                return eatJob;
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            return null;
        }
    }


}
