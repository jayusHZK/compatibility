package com.jayus.leetCode.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2022/7/6 9:31
 * @description :
 **/
public class oom {
    public static void main(String[] args) {
        byte[] bytsArr = new byte[1024];
        List<byte[]> list = new ArrayList<>();
        while (true){
            list.add(new byte[1024]);
        }
    }
}
