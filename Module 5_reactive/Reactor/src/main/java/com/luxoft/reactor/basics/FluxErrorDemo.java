package com.luxoft.reactor.basics;

import reactor.core.publisher.Flux;

public class FluxErrorDemo {
    public static void main(String[] args) {
//        Flux.error(new RuntimeException("Something went wrong"))
//            .doOnError(error -> System.err.println("The error message is: " +
//            error.getMessage()))
//            .subscribe(x -> System.out.println("onNext should never be printed!"),
//                Throwable::printStackTrace,
//                () -> System.out.println("onComplete should never be printed!"));


        Flux.range(0, 5)
                .doOnNext(i -> {
                    if (i % 2 == 1) {
                        throw new IllegalStateException("Boom!");
                    }
                })
                // in case of error switch to another stream
//                .onErrorResume(e -> Flux.just(-1, -2, -3))
                // in case of error return value and complete
//                .onErrorReturn(-1)
                // in case of error run function and continue
//                .onErrorContinue((e, i) -> {
//                    System.out.println("Value '" + i + "' have triggered an error " + e);
//                })
                // in case of error start from the beginning
                .retry(2)
                .log("values")
                .blockLast();

    }
}
