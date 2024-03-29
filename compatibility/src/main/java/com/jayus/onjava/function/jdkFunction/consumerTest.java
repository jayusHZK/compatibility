package com.jayus.onjava.function.jdkFunction;

import com.jayus.vo.UserVO;

import java.util.function.Consumer;

/**
 * @author : h zk
 * @date : 2023/3/14 11:34
 * @description : 处理对象，处理方式使用匿名方法创建 无返回值
 **/
public class consumerTest {

    // 有参数无返回值 无法获取返回值
    static Consumer<String> c = new UserVO()::setUsername;


    public static void main(String[] args) {
        operatorString("name", name -> {
            System.out.println(name);
        });

        operatorString("name", name -> {
            System.out.println("consumer:" + name);
        }, 1, age -> {
            System.out.println("consumer2:" + age);
        });
        consumerTest consumerTest = new consumerTest();
        consumerTest.a(new Object(),consumerTest::operatorString2);
    }

    private void operatorString2(Object o) {
        System.out.println(1);
    }


    public static void operatorString(String name, Consumer<String> consumer) {
        consumer.accept(name);
    }


    public static void operatorString(String name, Consumer<String> consumer, Integer age, Consumer<Integer> consumer2) {
        consumer.accept(name);
        consumer2.accept(age);
        //consumer.andThen(consumer2).accept(name);
    }

    public void a(Object t,Consumer<Object> consumer){
        consumer.accept(t);
    }



}
