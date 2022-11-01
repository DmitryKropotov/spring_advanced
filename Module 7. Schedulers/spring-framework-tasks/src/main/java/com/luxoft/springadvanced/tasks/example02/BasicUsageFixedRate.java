package com.luxoft.springadvanced.tasks.example02;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class BasicUsageFixedRate {
    @Scheduled(fixedRate = 3000)
    public void execute() {
        System.out.println("Executed every 3 seconds. Current time: " + new Date());
    }
}
