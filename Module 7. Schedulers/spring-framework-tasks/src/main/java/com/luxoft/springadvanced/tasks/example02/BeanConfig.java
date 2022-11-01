package com.luxoft.springadvanced.tasks.example02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class BeanConfig {
    @Bean
    public BasicUsageFixedRate basicUsageFixedRateBean() {
        return new BasicUsageFixedRate();
    }
}
