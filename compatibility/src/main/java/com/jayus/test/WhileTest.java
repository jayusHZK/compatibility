package com.jayus.test;

import java.util.ArrayList;
import java.util.List;

public class WhileTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        String a = list.get(0);
        int i = 0;
        while (list.contains(a) && i < 4){
            a = list.get(i);
            i++;
            a = null;
        }
        System.out.println(a);
    }
}
