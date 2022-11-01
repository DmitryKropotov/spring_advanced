package com.luxoft.springadvanced.springrest;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("counter")
public class IncrementController {
    AtomicLong counter = new AtomicLong();

    @GetMapping("link")
    public String getLink() {
        return "<a href='cached'>cached counter</a>";
    }

    // To test REST JS call, type in browser JS console:
    // await(await fetch("http://localhost:8081/counter/cached")).text()

    @GetMapping("cached")
    public Long getCounter(HttpServletResponse response) {
        response.setHeader(HttpHeaders.CACHE_CONTROL,
                CacheControl.maxAge(Duration.ofSeconds(10))
                        .cachePublic()
                        .mustRevalidate()
                        .getHeaderValue());
        return counter.getAndIncrement();
    }

    @GetMapping("cached2")
    public Long getCounterHeader(HttpServletResponse response) {
        response.setHeader("Cache-Control", "public, max-age=5, no-transform");
        return counter.getAndIncrement();
    }

    @GetMapping("cached3")
    public Long getCounterPrivateCache(HttpServletResponse response) {
        response.setHeader("Cache-Control", "private, max-age=5, no-transform");
        return counter.getAndIncrement();
    }

    @GetMapping("cached4")
    public ResponseEntity<Long> getCounterResponseEntity() {
        CacheControl cacheControl = CacheControl
                .maxAge(20, TimeUnit.SECONDS)
                .noTransform().mustRevalidate();
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .lastModified(Instant.now())
                .body(counter.getAndIncrement());
    }

    @GetMapping("notcached")
    public String getCounterNoCache(HttpServletResponse response) {
        return String.valueOf(counter.getAndIncrement());
    }
}

/*
 * To configure NGINX as cache proxy, use
 * proxy_cache_path /var/nginx keys_zone=my_cache:1m;
 * and then
 * location /app1 {
 *   	       proxy_pass http://localhost:8081/;
 * 	           proxy_cache my_cache;
 * }
 * See details at:
 * https://docs.nginx.com/nginx/admin-guide/content-cache/content-caching/
 */