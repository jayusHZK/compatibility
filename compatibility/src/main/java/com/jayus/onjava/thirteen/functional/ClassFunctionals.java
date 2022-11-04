package com.jayus.onjava.thirteen.functional;

import com.google.common.cache.Weigher;

import java.util.Comparator;
import java.util.function.Supplier;

/**
 * @author : h zk
 * @date : 2022/8/18 19:47
 * @description :
 **/
public class ClassFunctionals {
    static class AA{}
    static class BB{}
    static class CC{}
    static AA f1() { return new AA(); }
    static int f2(AA aa1, AA aa2) { return 1; }
    static void f3(AA aa) {}
    static void f4(AA aa, BB bb) {}
    static CC f5(AA aa) { return new CC(); }
    static CC f6(AA aa, BB bb) { return new CC(); }
    static boolean f7(AA aa) { return true; }
    static boolean f8(AA aa, BB bb) { return true; }
    static AA f9(AA aa) { return new AA(); }
    static AA f10(AA aa1, AA aa2) { return new AA(); }

    public static void main(String[] args) {
        Supplier<AA> f1 = ClassFunctionals::f1;
        AA aa = f1.get();
        Comparator<AA> f2 = ClassFunctionals::f2;
        f2.compare(new AA(),new AA());
    }
}
