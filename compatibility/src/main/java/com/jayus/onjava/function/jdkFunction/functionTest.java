package com.jayus.onjava.function.jdkFunction;

import com.jayus.vo.UserVO;

import java.util.function.Function;

/**
 * @author : h zk
 * @date : 2023/3/14 14:31
 * @description : 有返回值
 **/
public class functionTest {

    // 传入参数,返回值 有入参 有返回值
    Function<String,Integer> f = new UserVO()::setString;

    public static void main(String[] args) {
        System.out.println(convert("5", (str -> {
            Integer integer = Integer.valueOf(str);
            return integer;
        })));
        System.out.println(convert("5", (str -> {
            return Integer.valueOf(str);
        }), (inte -> {
            return inte.toString();
        })));
    }

    public static Integer convert(String str, Function<String, Integer> function) {
        return function.apply(str);
    }

    public static String convert(String str, Function<String, Integer> function, Function<Integer, String> function2) {
        Integer apply = function.apply(str);
        return function2.apply(apply);
    }

}
