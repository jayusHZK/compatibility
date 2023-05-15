package com.jayus.vo;

/**
 * @author : h zk
 * @date : 2023/5/9 9:27
 * @description :
 **/
public class test {

    public static void main(String[] args) {
        byte[][] arr = new byte[20][];
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            arr[i] = new byte[1024*1024];
        }
    }

}
