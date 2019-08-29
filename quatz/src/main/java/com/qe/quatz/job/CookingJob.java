package com.qe.quatz.job;

import com.qe.quatz.Jobhelper.JobPropertiesHelper;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author <a href="mailto:v-ksong@expedia.com">ksong</a>
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class CookingJob implements Job {
    private JobPropertiesHelper jobPropertiesHelper;
    private String cook;
    private String food;

    public JobPropertiesHelper getJobPropertiesHelper() {
        return jobPropertiesHelper;
    }

    public void setJobPropertiesHelper(JobPropertiesHelper jobPropertiesHelper) {
        this.jobPropertiesHelper = jobPropertiesHelper;
    }

    public String getCook() {
        return cook;
    }

    public void setCook(String cook) {
        this.cook = cook;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
//        setCook(jobDataMap.get("cook").toString());
//        setFood(jobDataMap.get("food").toString());
        System.out.println("*************************Cooking job*****************************");
        System.out.println(new Date());
        System.out.println(cook + " should go to cook for your dinner");
        System.out.println("1. cooking rice");
        System.out.println("2. clean your food: " + food);
        System.out.println("3. cooking your delicious food");
        System.out.println("**********************************************************");
    }
}
