package com.jayus.onjava.function.currying;

import java.util.function.Function;

/**
 * @author : h zk
 * @date : 2022/7/22 18:05
 * @description :
 **/
public class CurryingAndPartials {

    //未柯里化
    static String uncurried(String a,String b){
        return a + b;
    }

    public static void main(String[] args) {
        Function<String,Function<String,String>> sum =
                a -> b -> a + b;

        System.out.println(uncurried("hi","ho"));

        Function<String,String> hi = sum.apply("hi ");
        System.out.println(hi.apply("ho"));
        Function<String, String> apply = sum.apply("12");
        //IntUnaryOperator

    }

}
