package com.jayus.proxy.jdk;

public class Docker implements People {

    @Override
    public void doWhat(String s) {
        System.out.println(s);
    }
}
