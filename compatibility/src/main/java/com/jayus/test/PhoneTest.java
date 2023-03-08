package com.jayus.test;

public class PhoneTest {

    public static void main(String[] args) {
        String phone = "18538598785";
        for (int i = 0; i < phone.length(); i++) {
            System.out.println(phone.substring(i,i+1));
        }
        System.out.println(phone.substring(0,3)+"****"+phone.substring(7));
    }

}
