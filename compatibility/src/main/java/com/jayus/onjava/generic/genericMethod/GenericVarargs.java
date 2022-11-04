package com.jayus.onjava.generic.genericMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2022/8/9 16:18
 * @description :
 **/
public class GenericVarargs {
    public static <T>List<T> makeList(T... args){
        List<T> result = new ArrayList<>();
        for (T t : args) {
            result.add(t);
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list = makeList("A");
        System.out.println(list);
        list = makeList("A","B","C");
        System.out.println(list);
    }
}
