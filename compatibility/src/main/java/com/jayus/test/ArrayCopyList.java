package com.jayus.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : h zk
 * @date : 2022/12/5 15:49
 * @description :
 **/
public class ArrayCopyList {
    public static void main(String[] args) {
        String[] arr = {"a","b"};
        String[] arr1 = new String[10];
        //list1.add("c");
        System.arraycopy(arr,0,arr1,0,2);
        System.out.println(Arrays.toString(arr1));
    }
}
