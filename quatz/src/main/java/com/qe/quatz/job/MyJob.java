package com.qe.quatz.job;

import com.qe.quatz.service.ISing;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author <a href="mailto:v-ksong@expedia.com">ksong</a>
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class MyJob implements Job {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("***************************MY JOB*****************************");
        System.out.println(new Date());
        System.out.println(userName + "'s job is executed");
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println("I am number :" + i);
        }
        System.out.println("**********************************************************");
    }

    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // 开始
            JobKey jobKey = new JobKey("test3" , "test-3");
            if(scheduler.checkExists(jobKey)) {
                scheduler.getJobDetail(jobKey).getJobBuilder().usingJobData("userName", "Jay").build();
                scheduler.start();
            }
            // job 唯一标识 test.test-1
//            JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(jobKey).build();
//            Trigger trigger = TriggerBuilder.newTrigger()
//                    .withIdentity("test3" , "test")
//                    // 延迟一秒执行
//                    .startAt(new Date(System.currentTimeMillis() + 1000))
//                    // 每隔一秒执行 并一直重复
//                    .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
//                    .build();
//            scheduler.scheduleJob(jobDetail , trigger);

            Thread.sleep(90L*1000L);
            // 删除job
//            scheduler.deleteJob(jobKey);
//            scheduler.shutdown();
        } catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
