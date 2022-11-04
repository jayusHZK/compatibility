package com.jayus.onjava.generic.dynamicType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : h zk
 * @date : 2022/8/11 9:56
 * @description :
 **/
public class CheckList {

    @SuppressWarnings("unchecked")
    static void oldStyleMethod(List probablyDogs){
        probablyDogs.add(new String());
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        oldStyleMethod(list1);
        List<Integer> integers = Collections.checkedList(new ArrayList<>(), Integer.class);
        try {
            oldStyleMethod(integers);
        } catch (Exception e) {
            System.out.println("catch");
            e.printStackTrace();
        }

    }
}
