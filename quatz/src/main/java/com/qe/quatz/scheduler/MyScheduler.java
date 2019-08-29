//package com.qe.quatz.scheduler;
//
//import com.qe.quatz.job.MyJob;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * @author <a href="mailto:v-ksong@expedia.com">ksong</a>
// */
//@Component
//public class MyScheduler {
//    //    @Autowired
////    private JobDetail myJob;
////    @Autowired
////    private Trigger myTrigger;
////    @Autowired
////    private JobDetail cookingJob;
////    @Autowired
////    @Qualifier("cookingJobTrigger")
////    private Trigger cookingJobTrigger;
////    @Autowired
////    private Scheduler jobScheduler;
//
//    public void scheduleMyJob() {
////            scheduler.scheduleJob(myJob,myTrigger);
////            scheduler.scheduleJob(cookingJob,cookingJobTrigger);
////            scheduler.start();
//        try {
////            org.quartz.Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//            // 开始
//            jobScheduler.start();
//            // job 唯一标识 test.test-1
//            JobKey jobKey = new JobKey("test3", "test-3");
//            JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(jobKey).build();
//            Trigger trigger = TriggerBuilder.newTrigger()
//                    .withIdentity("test3", "test-3")
//                    // 延迟一秒执行
//                    .startAt(new Date(System.currentTimeMillis() + 1000))
//                    // 每隔一秒执行 并一直重复
//                    .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
//                    .build();
//            jobScheduler.scheduleJob(jobDetail, trigger);
//            Thread.sleep(10000);
//            jobScheduler.pauseJob(jobKey);
//            System.out.println("pause my job");
//
//            Thread.sleep(90L * 1000L);
//            // 删除job
////            scheduler.deleteJob(jobKey);
//            jobScheduler.shutdown();
//        } catch (SchedulerException | InterruptedException e) {
//            e.printStackTrace();
//        }
////            scheduler.shutdown();
//    }
////    public static void main(String[] args) {
////        try {
////            org.quartz.Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
////            // 开始
////            scheduler.start();
////            // job 唯一标识 test.test-1
////            JobKey jobKey = new JobKey("test3" , "test-3");
////            JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(jobKey).build();
////            Trigger trigger = TriggerBuilder.newTrigger()
////                    .withIdentity("test3" , "test-3")
////                    // 延迟一秒执行
////                    .startAt(new Date(System.currentTimeMillis() + 1000))
////                    // 每隔一秒执行 并一直重复
////                    .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
////                    .build();
////            scheduler.scheduleJob(jobDetail , trigger);
////
////            Thread.sleep(90L*1000L);
////            // 删除job
//////            scheduler.deleteJob(jobKey);
////            scheduler.shutdown();
////        } catch (SchedulerException | InterruptedException e) {
////            e.printStackTrace();
////        }
////    }
//}
