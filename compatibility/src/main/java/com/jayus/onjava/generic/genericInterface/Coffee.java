package com.jayus.onjava.generic.genericInterface;

/**
 * @author : h zk
 * @date : 2022/8/9 9:47
 * @description :
 **/
public class Coffee {
    private static long counter = 0;
    private static long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}
