package com.luxoft.reactor.basics;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

public class SubscribeDemo {

    public static void main(String[] args) {
        Mono.empty().subscribe(System.out::println);
        Mono.just("example").subscribe(System.out::println);

        Flux.generate(AtomicInteger::new,
                (item, square) -> {
                    int i = item.getAndIncrement();
                    square.next(i * i);
                    if (i == 10) square.complete();
                    return item;
                }).subscribe(System.out::println);

        Flux.create(sink -> {
            for (int i = 0; i < 3; i++) {
                sink.next("i=" + i);
            }
            sink.complete();
        }).subscribe(System.out::println);


        Flux<String> locations =
                Flux.just("Bucharest", "Krakow", "Moscow", "Kiev",
                        "Sofia");
        locations
                .doOnNext(s -> System.out.println(s))
                .map(String::length)
                .filter(l -> l >= 5)
                .subscribe(l -> System.out.println("Length: " + l),
                        Throwable::printStackTrace,
                        () -> System.out.println("Done."));


    }
}
