package com.jayus.onjava.generic.genericMethod;

/**
 * @author : h zk
 * @date : 2022/8/9 16:14
 * @description :
 **/
public class GenericMethods {
    public <T>void f(T t){
        System.out.println(t.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f("");
        gm.f(1);
        gm.f(1D);
        gm.f(1F);
        gm.f(1L);
        gm.f('a');
        gm.f(gm);
    }
}
