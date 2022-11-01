package com.luxoft.reactor.basics;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class FlatMapDemo {
    public static Flux<String> remoteRequest(String s) {
        return Flux.just("("+s+")");
    }

    static long delay = 500;
    public static Flux<String> remoteRequest2(String s) {
        delay -= 100;
        return Flux.just("("+s+") ("+delay+")")
                .delayElements(Duration.ofMillis(delay));
    }

    public static void main(String[] args) throws InterruptedException {
        Flux<String> locations =
                Flux.just("Bucharest", "Krakow",
                        "Moscow", "Kiev", "Sofia");
        locations
            .concatMap(s -> remoteRequest2(s))
            .subscribe(System.out::println);

        Thread.sleep(2000);
    }
}
