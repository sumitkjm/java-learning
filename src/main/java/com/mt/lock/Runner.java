package com.mt.lock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;

    private Lock lock = new ReentrantLock();

    private Condition cond = lock.newCondition();

    private void increment() {
        for (int i=0;i<10000;i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("first Thread: Waiting..");
        cond.await();
        System.out.println("First Thread: Woken up!");
        try {
            increment();
        } finally {
            lock.unlock();
            System.out.println("First Thread: Done");
        }

    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();

        System.out.println("Second Thread: Press the return key!");
        new Scanner(System.in).nextLine();

        System.out.println("Second Thread: Got return key!");
        cond.signal();
        try {
            increment();
        } finally {
            lock.unlock();
            System.out.println("Second Thread: Done");
        }
    }

    public void finished() {
        System.out.println("Count is: " +count);
    }
 }
