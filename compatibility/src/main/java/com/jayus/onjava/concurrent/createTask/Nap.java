package com.jayus.onjava.concurrent.createTask;

import java.util.concurrent.TimeUnit;

/**
 * @author : h zk
 * @date : 2022/8/15 14:05
 * @description :
 **/
public class Nap {
    public Nap(double t){
        try {
            TimeUnit.MILLISECONDS.sleep((int)(1000 * t));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Nap(double t,String msg){
        this(t);
        System.out.println(msg);
    }
}
