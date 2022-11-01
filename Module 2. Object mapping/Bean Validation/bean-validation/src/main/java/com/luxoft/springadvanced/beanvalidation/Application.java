package com.luxoft.springadvanced.beanvalidation;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner initDatabase(PersonRepository repository) {
        return args -> {
            repository.save(new Person("John Smith", "Australia", new BigDecimal("1541")));
            repository.save(new Person("Michael Johnson", "United Kingdom", new BigDecimal("1969")));
            repository.save(new Person("Katie Sierra", "United States", new BigDecimal("4799")));
        };
    }
}