package com.jayus.onjava.serialization;

import java.io.Serializable;

/**
 * @author : h zk
 * @date : 2022/8/17 18:20
 * @description :
 **/
public class Data implements Serializable {
    private int n;

    public Data(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }
}
