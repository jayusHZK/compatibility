package com.jayus.onjava.concurrent.createTask;

/**
 * @author : h zk
 * @date : 2022/8/15 14:04
 * @description :
 **/
public class NapTask implements Runnable {

    final int id;

    public NapTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        new Nap(0.1);
        System.out.println(this + " " + Thread.currentThread().getName());
    }

    @Override
    public String toString() {
        return "NapTask{" +
                "id=" + id +
                '}';
    }
}
