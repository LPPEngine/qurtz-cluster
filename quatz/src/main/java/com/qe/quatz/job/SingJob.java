package com.qe.quatz.job;

import com.qe.quatz.JobConstant;
import com.qe.quatz.Jobhelper.JobPropertiesHelper;
import javafx.application.Application;
import org.apache.catalina.core.ApplicationContext;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author <a href="mailto:v-ksong@expedia.com">ksong</a>
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SingJob extends QuartzJobBean {

    private JobPropertiesHelper jobPropertiesHelper;

    public JobPropertiesHelper getJobPropertiesHelper() {
        return jobPropertiesHelper;
    }

    public void setJobPropertiesHelper(JobPropertiesHelper jobPropertiesHelper) {
        this.jobPropertiesHelper = jobPropertiesHelper;
    }

    private String singer;

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        //record to jobKey to put relative job properties values

        System.out.println("**************************Sing Job*****************************");
        System.out.println(new Date());
        System.out.println(jobKey + " "+ singer + " is eating!");
        System.out.println("**********************************************************");
        jobDataMap.put(JobConstant.SINGER,jobPropertiesHelper.getSinger());
    }
}
