package com.luxoft.springadvanced.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    add implements WebMvcConfigurer to Application
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        WebContentInterceptor interceptor = new WebContentInterceptor();
//        interceptor.addCacheMapping(
//                CacheControl.maxAge(60, TimeUnit.SECONDS)
//                .noTransform()
//                .mustRevalidate(),
//                "/counter/*");
//        registry.addInterceptor(interceptor);
//    }

}
