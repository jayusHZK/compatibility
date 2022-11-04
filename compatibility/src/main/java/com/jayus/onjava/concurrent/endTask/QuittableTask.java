package com.jayus.onjava.concurrent.endTask;

import com.jayus.onjava.concurrent.createTask.Nap;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author : h zk
 * @date : 2022/8/15 16:16
 * @description :
 **/
public class QuittableTask implements Runnable{
    final int id;
    public QuittableTask(int id) {
        this.id = id;
    }

    public void quit(){
        running.set(false);
    }

    private AtomicBoolean running = new AtomicBoolean(true);

    @Override
    public void run() {
        while (running.get()){
            new Nap(0.1);
        }
        System.out.println(id + " ");
    }
}
