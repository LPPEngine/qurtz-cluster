package com.qe.quatz;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * @author <a href="mailto:v-ksong@expedia.com">ksong</a>
 */
@Component
@Lazy
@Deprecated
public class TestBeanCreate implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("I am initial");
    }
    public void show(){
        System.out.println("My show time");
    }

    public static void main(String[] args) {
//        LocalTime localTime = LocalTime.of(23,59);
        LocalDateTime localDateTime = LocalDateTime.of(2019,7,31,23,59);
        long now = System.currentTimeMillis();
        long start = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long delayUtilStart = ((start - now) / 1000 + 24*60*60) % 24*60*60;
        System.out.println(delayUtilStart);

    }
}
