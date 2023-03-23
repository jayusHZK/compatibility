package com.jayus.test;

/**
 * @author : h zk
 * @date : 2023/3/21 10:14
 * @description :
 **/
public class ExceptionTest {

    public static void main(String[] args) {
        String a = "75257DB9-BB8F-4368-8777-F190FCA45B01";
        System.out.println(a.length());
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            if (e instanceof ArithmeticException){
                System.out.println(1);
            }
        }
    }

}
