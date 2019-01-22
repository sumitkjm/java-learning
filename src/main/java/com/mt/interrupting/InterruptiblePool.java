package com.mt.interrupting;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InterruptiblePool {

    public static void main(String[] args) throws  Exception {
        System.out.println("Starting..");
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<?> future = executorService.submit(new Callable<Void>() {

            @Override
            public Void call() throws Exception {

                Random random = new Random();
                for (int i=0;i<1E6;i++) {
                    /*try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    if(Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted!");
                        break;
                    }
                    Math.sin(random.nextDouble());
                }
                return null;
            }
        });

        executorService.shutdown();
        Thread.sleep(50);
        future.cancel(true);
        executorService.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("Finished!");
    }
}
