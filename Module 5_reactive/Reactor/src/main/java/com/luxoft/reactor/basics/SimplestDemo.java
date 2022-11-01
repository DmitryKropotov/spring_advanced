package com.luxoft.reactor.basics;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class SimplestDemo {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> locations =
                Flux.just("Bucharest",
                        "Krakow", "Moscow",
                        "Kiev", "Sofia"); //declaration
        locations.map(s -> s.length())
                .subscribe(System.out::println);

        Flux<String> steps =
                Flux.interval(Duration.ofSeconds(1))
                        .map(i -> (new String[]{"Bucharest",
                                "Krakow", "Moscow",
                                "Kiev", "Sofia"})[Math.toIntExact(i % 5)])
                        .take(15);
        steps.subscribe(l ->
                System.out.println(l));

        Thread.sleep(20000);
    }
}
