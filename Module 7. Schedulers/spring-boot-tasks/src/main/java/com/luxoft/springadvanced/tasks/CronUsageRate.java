package com.luxoft.springadvanced.tasks;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class CronUsageRate {
    @Scheduled(cron = "*/3 * * * * ?")
    public void execute() {
        System.out.println("Executed every 3 seconds. Current time: " + new Date());
    }
}
