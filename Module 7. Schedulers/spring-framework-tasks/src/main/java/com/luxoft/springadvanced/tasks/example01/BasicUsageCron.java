package com.luxoft.springadvanced.tasks.example01;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class BasicUsageCron {
    @Scheduled(cron = "*/3 * * * * ?")
    public void execute() {
        System.out.println("Executed every 3 seconds. Current time: " + new Date());
    }
}
