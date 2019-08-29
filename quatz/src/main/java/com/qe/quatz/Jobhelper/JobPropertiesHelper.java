package com.qe.quatz.Jobhelper;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author <a href="mailto:v-ksong@expedia.com">ksong</a>
 */
@Component
public  class JobPropertiesHelper implements Serializable{
    @Value("${cook}")
    String cook;
    @Value("${food}")
    String food;
    @Value("${singer}")
    String singer;

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
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
}
