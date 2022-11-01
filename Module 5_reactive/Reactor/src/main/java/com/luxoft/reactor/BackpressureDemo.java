package com.luxoft.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class BackpressureDemo {
    public static void main(String[] args) {
        Scheduler parallel = Schedulers.parallel();
        // buffer size for publishOn is 256 by default
        System.setProperty("reactor.bufferSize.small", "16");
        Flux.range(1, 300)
                // backpressure by default - unbounded buffer
                //.onBackpressureBuffer(100)
                //.onBackpressureDrop()
                //.onBackpressureLatest()
                //.onBackpressureError()
                .publishOn(parallel)
                //.publishOn(parallel, 30)
                // we can set buffer size here -
                // it will override reactor.bufferSize.small
                .doOnNext(v -> {
                    sleep(30);
                    System.out.println("Received " + v);
                })
                .blockLast();
        parallel.dispose();
    }

    private static void sleep(long millis)  {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
