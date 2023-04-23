package com.jayus.test;

import java.util.Random;

/**
 * @author : h zk
 * @date : 2023/3/16 14:28
 * @description :
 **/
public class RandomTest {
    public static void main(String[] args) {
        System.out.println(new Random().nextInt(940) + 60);
        System.out.println(a());
    }

    public static String a(){
        String randomcode = "";
        for(int i=0;i<6;i++)
        {
            //52个字母与6个大小写字母间的符号；范围为91~96
            int value = (int)(Math.random()*58+65);
            while(value>=91 && value<=96)
                value = (int)(Math.random()*58+65);
            randomcode = randomcode + (char)value;

        }
        System.out.println(randomcode);

        //用字符数组的方式随机
        String randomcode2 = "";
        String model = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        char[] m = model.toCharArray();

        for (int j=0;j<6 ;j++ )
        {
            char c = m[(int)(Math.random()*62)];
            randomcode2 = randomcode2 + c;
        }

        return randomcode2;
    }
}
