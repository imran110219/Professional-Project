package com.sadman.inventory;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @author Sadman
 */
public class DemoService {
    @Scheduled(cron="*/5 * * * * ?")
    public static void demoServiceMethod()
    {
        System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
    }
}
