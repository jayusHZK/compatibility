package com.jayus.onjava.function.jdkFunction;

import com.jayus.vo.UserVO;

import java.util.function.Predicate;

/**
 * @author : h zk
 * @date : 2023/3/14 11:46
 * @description : 判断使用，判断方式写在匿名内部类中，有返回值  一般用来做判断 代替if/else
 **/
public class predicateTest {

    // 有入参返回boolean值
    Predicate<Integer> p = new UserVO()::isOld;

    public static void main(String[] args) {
        System.out.println(check("name", str -> {
            return str.equalsIgnoreCase("STR");
        }));
        System.out.println(check("name", str -> {
            return str.equalsIgnoreCase("NAME");
        }, 1, inte -> {
            return inte.equals(1);
        }));
    }

    public static boolean check(String str, Predicate<String> predicate){
        return predicate.test(str);
    }

    public static boolean check(String str,Predicate<String> predicate,Integer inte,Predicate<Integer> predicate2){
        //pre.and(pre1).test(str);
        return predicate.test(str) && predicate2.test(inte);
    }

}
