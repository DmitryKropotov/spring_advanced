package com.luxoft.webflux.config;

import com.luxoft.webflux.handlers.RootHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class HelloRouter {
    @Bean
    public RouterFunction<ServerResponse> route(RootHandler rootHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET(""),
                        rootHandler::root)
                .andRoute(RequestPredicates.GET("/hello"),
                        rootHandler::hello);
    }
}
