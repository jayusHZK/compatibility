package com.jayus.onjava.thirteen.X;

/**
 * @author : h zk
 * @date : 2022/7/19 10:40
 * @description :
 **/
public class UnboundMethodReference {
    public static void main(String[] args) {
        // MakeString ms = X::f;
        TransformX sp = X::f;
        X x = new X();
        System.out.println(sp.tranform(x));
        System.out.println(x.f());
        MakeString ms = x::f;
        System.out.println(ms.make());
    }
}
