package com.jayus.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2022/12/3 17:22
 * @description :
 **/
public class SortTest {
    public static void main(String[] args) {

        String a = "icon/icon_1.png";
        System.out.println(a.split("/")[1].substring(5).split("\\.")[0]);

        List<String> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add("");
        }
        List<String> list1 = new ArrayList<>();
        list1.add("");
        list.removeAll(list1);
        System.out.println(list.size());
        // 先获取数组
        // 数字排序
        // 根据数字获取文件
    }
}
