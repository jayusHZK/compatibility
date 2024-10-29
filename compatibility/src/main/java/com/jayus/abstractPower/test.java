package com.jayus.abstractPower;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @ClassName test
 * @Description: 这种传方法引用可操作空间很大，比如加锁、事务、
 * @date: 2024/10/26 01:07
 *  没有参数和返回值的用 callback
 *  有参数没返回值的用 consumer
 *  有返回值没参数的用 supplier
 *  有参数返回boolean的用 predicate
 *  有参数有返回值的用 function
 */
public class test {

    public static void main(String[] args) {
        //consumer("a", test::set);
        //System.out.println(predicate("a", test::isName));
        //System.out.println(function("min", test::append));
        //System.out.println(supplier(test::get));
        runnable(test::more);
        Function f = (Function) new test();
    }



    public static void more(){
        System.out.println(1);
    }

    //没参数没返回值
    public static void runnable(Runnable r){
        r.run();
    }

    public static void set(String a) {
        System.out.println(a);
    }

    // 有入参 无返回值
    public static <T> void consumer(T t, Consumer<T> c) {

        //lock.lock
        c.accept(t);
        //c.andThen()
        //lock.unlock
    }

    public static String get(){
        return "a";
    }

    // 只有返回值
    public static <T> T supplier(Supplier<T> s){
        return s.get();
    }

    public static boolean isName(String a) {
        return a.equals("name");
    }

    // 有入参 返回 boolean值
    public static <T> boolean predicate(T t, Predicate<T> p) {
        // 封装一个比较函数
        Predicate<String> pre = Predicate.isEqual("Hello");
        boolean test = p.test(t);
        return test;
    }



    public static String append(String a) {
        return "Hi! " + a;
    }

    // 有入参有出参
    public static <T,V> V function(T a, Function<T,V> t) {
        // 返回原值
        Function<Object, Object> identity = Function.identity();
        return t.apply(a);
    }




}
