package com.qe.quatz.job;

import com.qe.quatz.Jobhelper.JobPropertiesHelper;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * @author <a href="mailto:v-ksong@expedia.com">ksong</a>
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class TriggerSpringBatchJob implements Job {
    @Autowired
    JobPropertiesHelper jobPropertiesHelper;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        try {
//            URL url = new URL("http://localhost:8080/invokejob");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            StringBuilder result = new StringBuilder();
//            String string;
//            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
//                    while ((string = bufferedReader.readLine()) != null) {
//                        result.append(string);
//                    }
//                }
//                System.out.println(result);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("*******************Spring Batch Job*************************");
        System.out.println(new Date());
        System.out.println("My task is calling spring batch jobs to run");
        System.out.println("************************************************");

    }
}
