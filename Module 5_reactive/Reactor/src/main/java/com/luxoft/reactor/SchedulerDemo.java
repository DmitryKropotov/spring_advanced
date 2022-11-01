package com.luxoft.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class SchedulerDemo {

    public static void main(String[] args) throws InterruptedException {
        // Creates a new Scheduler backed by four Thread instances.
        Scheduler s4 = Schedulers.newParallel(
                "parallel-scheduler", 4);
//        Scheduler s4 = Schedulers.parallel();
        Scheduler s1 = Schedulers.newSingle("single");

        // tip: use debugger to see what thread pool is used for each operation
        final Flux<String> flux = Flux
                .range(1, 3)
                // runs on the anonymous thread
                .map(i -> 10 + i)
                // The publishOn switches the sequence to single-scheduler
                .publishOn(s1)
                // defines scheduler for operators before publishOn
                .subscribeOn(s4)
                .map(i -> "value " + i);

        flux.subscribe(s -> System.out.println(Thread.currentThread().getName() + " " + s));

        final Flux<String> flux2 = Flux
                .range(3, 2)
                // runs on the anonymous thread
                .map(i -> 10 + i)
                // The publishOn switches the sequence to parallel-scheduler
//                .publishOn(s4)
                // defines scheduler for operators before publishOn
                .subscribeOn(s4)
                .map(i -> "value " + i);

        flux2.subscribe(s -> System.out.println(Thread.currentThread().getName() + " " + s));

         Thread.sleep(100000);
         s4.dispose();
         s1.dispose();
    }
}
