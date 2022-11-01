package com.luxoft.springadvanced.springrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    Environment environment;

    @Autowired
    private ApplicationArguments args;

    @GetMapping
    public String hello() {
        String port = environment.getProperty("local.server.port");
        if (args.getSourceArgs().length>0) {
            String arg0 = args.getSourceArgs()[0];
            return "Hello from spring-rest app running on " + port + " with argument " + arg0;
        } else {
            return "Hello from spring-rest app running on " + port;
        }
    }
}
