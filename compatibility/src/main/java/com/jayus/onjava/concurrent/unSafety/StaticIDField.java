package com.jayus.onjava.concurrent.unSafety;

/**
 * @author : h zk
 * @date : 2022/8/16 13:57
 * @description :
 **/
public class StaticIDField implements HasID{
    private static int counter;

    private int id = counter++;

    @Override
    public int getID() {
        return id;
    }
}
