package com.jayus.onjava.concurrent.deadlock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author : h zk
 * @date : 2022/8/16 10:26
 * @description :
 **/
public class StickHolder {
    private static class Chopstick{

    }

    private Chopstick stick = new Chopstick();

    private BlockingQueue<Chopstick> holder = new ArrayBlockingQueue<>(1);

    public void pickUp(){
        try {
            holder.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void putDown(){
        try {
            holder.put(stick);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
