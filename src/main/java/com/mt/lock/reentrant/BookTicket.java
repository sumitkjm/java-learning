package com.mt.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class BookTicket {

    private static ReentrantLock lock = new ReentrantLock();

}
