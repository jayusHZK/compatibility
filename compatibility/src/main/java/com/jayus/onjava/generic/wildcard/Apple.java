package com.jayus.onjava.generic.wildcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author : h zk
 * @date : 2022/8/10 17:22
 * @description :
 **/
public class Apple extends Fruit{

    public static void main(String[] args) {
        List<? extends Fruit> list = Arrays.asList(new Apple());
        Apple fruit = (Apple) list.get(0);
        System.out.println(fruit.getClass().getSimpleName());
        list.contains(new Apple());
        list.indexOf(new Apple());
        List<Fruit> list1 = new ArrayList<>();
        list1.add(new Apple());
        Collections.checkedList(new ArrayList<>(),fruit.getClass());
    }
}
