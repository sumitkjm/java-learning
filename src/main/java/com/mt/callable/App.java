package com.mt.callable;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        Future<Integer> future = executorService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                System.out.println("Starting..");
                int duration = random.nextInt(4000);

                if(duration>2000) {
                    throw new IOException("Sleeping too long...");
                }

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished!");

                return duration;
            }
        });

        executorService.shutdown();

        try {
            System.out.println("Result is: " + future.get());
        }catch (ExecutionException|InterruptedException e) {
            e.printStackTrace();
        }
        ExecutorService executorServiceVoid = Executors.newCachedThreadPool();


        Future<?> futureVoid = executorService.submit(new Callable<Void>() {

            @Override
            public Void call() throws Exception {
                Random random = new Random();
                System.out.println("Starting..");
                int duration = random.nextInt(4000);

                if(duration>2000) {
                    throw new IOException("Sleeping too long...");
                }

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished!");

                return null;
            }
        });

        executorServiceVoid.shutdown();

        try {
            System.out.println("Result is: " + futureVoid.get());
        }catch (ExecutionException|InterruptedException e) {
            e.printStackTrace();
        }

    }
}
