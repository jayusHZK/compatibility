package com.jayus.onjava.nineteen.initable;

/**
 * @author : h zk
 * @date : 2022/8/1 17:45
 * @description :
 **/
public class Initable {
    static final int STATIC_FINAL = 47;
    static final int STATIC_FINAL2=ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("Initable init");
    }
}
