package com.qe.quatz.manager;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:v-ksong@expedia.com">ksong</a>
 */
@RestController
@Slf4j
public class JobManager {
    private Logger logger = LoggerFactory.getLogger(JobManager.class);
    @Autowired
    private Scheduler jobScheduler;
    @GetMapping(value = "stopDesignatedJob")
    @ResponseStatus(HttpStatus.OK)
    public String stopDesignatedJob(@RequestParam String jobName,@RequestParam String jobGroup){
        if(StringUtils.isEmpty(jobName)){
            return "JobName can not be null";
        }
        JobKey designatedJobKey = new JobKey(jobName,jobGroup);
        try {
            if(!jobScheduler.checkExists(designatedJobKey)){
                return "No this designated job!";
            }
            if (!jobScheduler.isShutdown()) {
                jobScheduler.pauseJob(designatedJobKey);
            }
        } catch (SchedulerException e) {
            logger.error("stop designated job exception",e);
            return "stop designated job exception";
        }
        return "Stop " + jobName + " successfully!";
    }
    @GetMapping(value = "startDesignatedJob")
    @ResponseStatus(HttpStatus.OK)
    public String startDesignatedJob(@RequestParam String jobName,@RequestParam String jobGroup){
        if(StringUtils.isEmpty(jobName)){
            return "JobName can not be null";
        }
        JobKey designatedJobKey = new JobKey(jobName,jobGroup);
        try {
            if(!jobScheduler.checkExists(designatedJobKey)){
                return "No this designated job!";
            }
            if (!jobScheduler.isShutdown()) {
                jobScheduler.resumeJob(designatedJobKey);
            }
        } catch (SchedulerException e) {
            logger.error("resume designated job exception",e);
            return "resume designated job exception";
        }
        return "resume  " + jobName + " successfully!";
    }
    @GetMapping(value = "allJobDetails")
    @ResponseStatus(HttpStatus.OK)
    public String allJobDetails(){
        List<String> allJobDetailList = new ArrayList<>();
        try {
            List<String> jobGroup = jobScheduler.getJobGroupNames();
            Set<JobKey> jobKeySet;

            for (int i = 0 , size = jobGroup.size(); i < size; i++) {
                jobKeySet = jobScheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroup.get(i))) ;
                for (JobKey jobKey : jobKeySet){
                    allJobDetailList.add(jobKey.getGroup() + " :" + jobKey.getName() + jobScheduler.getJobDetail(jobKey));
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return allJobDetailList.toString();
    }
    @GetMapping(value = "deleteDesignatedJob")
    @ResponseStatus(HttpStatus.OK)
    public String deleteDesinatedjob(@RequestParam String jobName,@RequestParam String jobGroup){
        JobKey jobKey = new JobKey(jobName,jobGroup);
        try {

            if (jobScheduler.checkExists(jobKey) ) {
                jobScheduler.deleteJob(jobKey);
            }else {
                return "NO this job";
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "delete successfully";
    }
    @GetMapping(value = "modifyDesignatedJobTrigger")
    @ResponseStatus(HttpStatus.OK)
    public String modifyDesignatedJobTrigger(@RequestParam String jobName , @RequestParam String jobGroup,@RequestParam String trigger ,@RequestParam String triggerGroup , @RequestParam String cron) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName,jobGroup);
        TriggerKey triggerKey;
        if(jobScheduler.checkExists(jobKey)){
            triggerKey = TriggerKey.triggerKey(trigger,triggerGroup);
            CronTrigger oldTrigger = (CronTrigger) jobScheduler.getTrigger(triggerKey);
            if(oldTrigger == null){
                return "No this trigger!";
            }else {
                if(!oldTrigger.getCronExpression().equalsIgnoreCase(cron)){
                    Trigger newTrigger = TriggerBuilder.newTrigger()
                            .withIdentity(trigger,triggerGroup)
                            .startNow()
                            .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                            .build();
                    jobScheduler.rescheduleJob(triggerKey,newTrigger);
                    return "modify" + triggerKey + "'s trigger successfully!";
                }else {
                    return "modify" + triggerKey + "'s trigger successfully!";
                }
            }

        }else {
            return "No this job";
        }
    }
}
