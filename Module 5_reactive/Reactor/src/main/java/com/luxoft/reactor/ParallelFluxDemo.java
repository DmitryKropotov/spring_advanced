package com.luxoft.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class ParallelFluxDemo {
    public static void main(String[] args) {
        // this code is executed on a separate thread
        // (not main, but parallel-1), but this is a single thread
        Flux.range(1, 10)
                .publishOn(Schedulers.parallel())
                .subscribe(i -> System.out.println(
                        Thread.currentThread().getName() +
                                " -> " + i));

        sleep(1000);
        System.out.println();

//        Flux.range(1, 10)
//                .parallel(8)
//                .runOn(Schedulers.boundedElastic())
//                .subscribe(i -> System.out.println(
//                        Thread.currentThread().getName() +
//                                " -> " + i));

        // this code is executed on multiple threads
        // and the elements orders is not preserved
//        Flux.range(1, 10)
//                .parallel(2)
//                .runOn(Schedulers.boundedElastic())
//                .subscribe(i -> System.out.println(
//                        Thread.currentThread().getName() +
//                                " -> " + i));
//        sleep(1000);
    }

    private static void sleep(long millis)  {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
