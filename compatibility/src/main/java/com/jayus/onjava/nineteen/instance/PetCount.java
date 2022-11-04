package com.jayus.onjava.nineteen.instance;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author : h zk
 * @date : 2022/8/3 11:38
 * @description :
 **/
public class PetCount {
    public static void main(String[] args) {
        Pair pair = new Pair();
        // isInstance 传入类实例是否可以转换成class对象
        System.out.println(Pet.class.isInstance(pair));
        // isAssignableFrom 传入类是否是指定类子类
        System.out.println(Pet.class.isAssignableFrom(pair.getClass()));
        System.out.println(Pair.class.isAssignableFrom(Pet.class));
    }
}
