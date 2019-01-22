package com.mt.waitnotify;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Processor2 {

    private LinkedList<Integer> list = new LinkedList<>();

    private Object lock = new Object();

    private final int LIMIT = 10;

    public void produce() throws InterruptedException {
        int value =0;
        while (true) {
            synchronized (lock) {
                while (list.size()==LIMIT) {
                    System.out.println("Producer has reached threshold!");
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }

    }

    public void consume() throws InterruptedException {
        Random random = new Random();

        while (true) {
            synchronized (lock) {
                while(list.size()==0) {
                    System.out.println("All items consumed, waiting for producer to produce..");
                    lock.wait();
                }

                System.out.print("List size is: " + list.size());
                int value = list.removeFirst();
                System.out.println("; value is: " + value);
                lock.notify();
            }
//            Thread.sleep(random.nextInt(700));
        }
    }

}
