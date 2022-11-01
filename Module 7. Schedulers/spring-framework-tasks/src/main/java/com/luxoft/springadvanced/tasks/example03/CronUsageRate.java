package com.luxoft.springadvanced.tasks.example03;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class CronUsageRate {
    @Scheduled(cron = "0 15 10 15 * ?")
    public void execute() {
        System.out.println("Executed at 10:15 AM on the 15th day of every month. Current time: " + new Date());
    }
}
