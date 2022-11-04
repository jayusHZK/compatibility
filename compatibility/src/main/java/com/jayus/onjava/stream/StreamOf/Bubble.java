package com.jayus.onjava.stream.StreamOf;

/**
 * @author : h zk
 * @date : 2022/7/26 17:13
 * @description :
 **/
public class Bubble {

    Integer age;

    String name;

    public Bubble() {
    }

    public Bubble(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Bubble{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
