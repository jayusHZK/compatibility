package com.jayus.onjava.function.functionComosition;

import java.util.function.Function;

/**
 * @author : h zk
 * @date : 2022/7/22 17:24
 * @description :
 **/
public class FunctionComosition {
    static Function<String,String> f1 =
            s -> {
                System.out.println(s);
                return s.replace("A","_");
            },
    f2 = s -> s.substring(3),
    f3 = s -> s.toLowerCase(),
    f4 = f1.compose(f2).andThen(f3);

    public static void main(String[] args) {
        System.out.println(f4.apply("GO AFTER ALL AMBULANCES"));
    }
}
