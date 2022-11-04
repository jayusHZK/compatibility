package com.jayus.onjava.concurrent.createTask;

import java.util.concurrent.Callable;

/**
 * @author : h zk
 * @date : 2022/8/15 15:29
 * @description :
 **/
public class CountingTask implements Callable<Integer> {

    final int id;

    public CountingTask(int id) {this.id = id;}

    @Override
    public Integer call(){
        Integer val = 0;
        for (int i = 0; i < 100; i++) {
            val++;
        }
        System.out.println(id + " " +Thread.currentThread().getName()+ " " + val);
        return val;
    }
}



