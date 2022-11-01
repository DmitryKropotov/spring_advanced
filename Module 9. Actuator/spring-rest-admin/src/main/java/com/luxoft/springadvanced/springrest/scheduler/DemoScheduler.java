package com.luxoft.springadvanced.springrest.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class DemoScheduler {

    @Scheduled(fixedRate = 10000)
    public void someScheduler() {
    }

}
