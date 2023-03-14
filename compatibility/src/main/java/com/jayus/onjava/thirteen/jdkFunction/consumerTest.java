package com.jayus.onjava.thirteen.jdkFunction;

import java.util.function.Consumer;

/**
 * @author : h zk
 * @date : 2023/3/14 11:34
 * @description : 处理对象，处理方式使用匿名方法创建 无返回值
 **/
public class consumerTest {

    public static void main(String[] args) {
        operatorString("name", name -> {
            System.out.println(name);
        });

        operatorString("name", name -> {
            System.out.println("consumer:" + name);
        }, 1, age -> {
            System.out.println("consumer2:" + age);
        });
    }

    public static void operatorString(String name, Consumer<String> consumer) {
        consumer.accept(name);
    }

    public static void operatorString(String name, Consumer<String> consumer, Integer age, Consumer<Integer> consumer2) {
        consumer.accept(name);
        consumer2.accept(age);
        //consumer.andThen(consumer2).accept(name);
    }

}
