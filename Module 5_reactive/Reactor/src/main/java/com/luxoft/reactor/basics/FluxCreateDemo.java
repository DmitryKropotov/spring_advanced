package com.luxoft.reactor.basics;

import reactor.core.publisher.Flux;

public class FluxCreateDemo {
    public static void main(String[] args) {
        Flux<String> locations = Flux.create(location -> {
            location.next("Bucharest");
            location.next("Krakow");
            location.error(new RuntimeException("Moscow"));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            location.next("Kiev");
            location.next("Sofia");
            location.complete();
        });

    locations.subscribe(
                s -> System.out.println("Location: " + s),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("I'm completed"));

    }
}
