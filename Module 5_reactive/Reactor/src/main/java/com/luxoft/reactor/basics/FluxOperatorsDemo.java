package com.luxoft.reactor.basics;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class FluxOperatorsDemo {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("--- filter ---");
//        Flux.just("Bucharest", "Krakow",
//                "Moscow", "Kiev", "Sofia")
//        .filter(s -> s.length() >= 5)
//        .subscribe(s -> System.out.println("Location: " + s));
//
//        System.out.println("--- skip ---");
//        Flux.just("Bucharest", "Krakow",
//                "Moscow", "Kiev", "Sofia")
//        .skip(2)
//        .subscribe(s -> System.out.println("Location: " + s));
//
//        System.out.println("--- distinct ---");
//        Flux.just("Bucharest", "Krakow",
//                "Moscow", "Krakow", "Moscow",
//                "Kiev", "Sofia")
//        .distinct()
//        .subscribe(s -> System.out.println("Location: " + s));
//
//        System.out.println("--- distinctUntilChanged ---");
//        Flux.just("Bucharest", "Krakow",
//                "Moscow", "Moscow",
//                "Kiev", "Moscow", "Sofia")
//                .distinctUntilChanged()
//                .subscribe(s -> System.out.println("Location: " + s));

//        System.out.println("--- map ---");
//        Flux.just("Bucharest", "Krakow",
//                "Moscow", "Kiev", "Sofia")
//        .map(s->s.length())
//        .subscribe(l -> System.out.println("Length: " + l));
//
//        System.out.println("--- scan ---");
//        Flux.range(1, 10)
//        .scan((x, y) -> x+y)
//        .subscribe(sum -> System.out.println("Sum: " + sum));

//        Flux.just("Bucharest", "Krakow",
//                "Moscow", "Kiev", "Sofia")
//                .sort()
//                .subscribe(l -> System.out.println("Location: " + l));


        System.out.println("--- groupBy ---");
        Flux.just("Bucharest", "Krakow", "Moscow", "Kiev", "Sofia", "Kiev")
                .groupBy(s -> s.length())
                .subscribe(group -> {
                    System.out.println("subscribe to a new group: " + group.key());
                    Integer key = group.key();
                    group.subscribe(e -> System.out.println(key + ": " + e));
                });

        System.out.println("--- timestamp ---");
        Flux.interval(Duration.ofMillis(100))
                .take(3).timestamp()
                .subscribe(val -> {
                    System.out.print(Instant.ofEpochMilli(val.getT1()));
                    System.out.println(": " + val.getT2());
                });

        Thread.sleep(1000);
    }
}
