package com.mt.lock.semaphore;

import java.beans.IntrospectionException;
import java.util.concurrent.Semaphore;

public class Connection {

    private static Connection instance = new Connection();

    private Semaphore sem = new Semaphore(50, false);

    private int connections = 0;

    private Connection() {

    }

    public void connect() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doConnect();
        } finally {
            sem.release();
        }
    }

    public static Connection getInstance() {
        return instance;
    }

    public  void doConnect() {
        synchronized (this) {
            connections++;

            System.out.println("Current Connections: " +connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            connections--;

        }
    }
}
