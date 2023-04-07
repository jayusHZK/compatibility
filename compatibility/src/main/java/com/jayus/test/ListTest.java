package com.jayus.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2022/12/7 16:49
 * @description :
 **/
public class ListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.set(0,2);
        System.out.println(list.get(0));

        System.out.println(list.contains(1));
    }
}
