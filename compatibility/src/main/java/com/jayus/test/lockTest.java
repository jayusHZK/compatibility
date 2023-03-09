package com.jayus.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lockTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.lock();
        aa();
        lock.unlock();
        lock.unlock();
    }

    static void aa(){
        System.out.println(1);
    }

}
