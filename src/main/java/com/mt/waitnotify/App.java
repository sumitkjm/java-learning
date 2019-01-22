package com.mt.waitnotify;

public class App {

    public static  void main(String[] args) throws InterruptedException {

//        Processor processor = new Processor();
        Processor2 processor = new Processor2();

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();


    }
}
