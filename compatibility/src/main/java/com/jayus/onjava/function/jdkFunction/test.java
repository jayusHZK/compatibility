package com.jayus.onjava.function.jdkFunction;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author : h zk
 * @date : 2022/7/20 15:48
 * @description : 方法可以抽成 function接口
 **/
public class test {
    public static void main(String[] args) {
        // Supplier
        System.out.println(getString(() -> "aa"));
        System.out.println(getInteger(() -> 123));
        // Consumer
        operatorString("bb", (String name) -> {
            System.out.println(name);
        });
        operatorString("cc", name -> {
            System.out.println(name);
        });
        operatorString("dd", name -> {
            System.out.println(name);
        }, name -> {
            System.out.println(name + "a");
        });
        // Predicate
        System.out.println(checkString("aa", str -> {
            return str.equals("aa");
        }));
        System.out.println(checkString1("aa", str -> {
            return str.equals("aa");
        }));
        System.out.println(checkString("ab",str ->{
            return str.contains("a");
        },str ->{
            return str.contains("b");
        }));
        // Function
        convert("123",str -> Integer.valueOf(str));
        convert("465",str -> Integer.valueOf(str),i -> String.valueOf(i));

    }

    public static String getString(Supplier<String> sup) {
        return sup.get();
    }

    public static Integer getInteger(Supplier<Integer> sup) {
        return sup.get();
    }

    public static void operatorString(String name, Consumer<String> con) {
        con.accept(name);
    }

    public static void operatorString(String name, Consumer<String> con, Consumer<String> con1) {
        con.accept(name);
        con1.accept(name);
        // 上两行代码等价于
        //con.andThen(con1).accept(name);
    }

    public static boolean checkString(String str, Predicate<String> pre) {
        return pre.test(str);
    }

    public static boolean checkString1(String str, Predicate<String> pre) {
        //return !pre.test(str);
        // 等价与上一句
        return pre.negate().test(str);
    }

    public static boolean checkString(String str, Predicate<String> pre, Predicate<String> pre1) {
        boolean test = pre.test(str);
        boolean test1 = pre1.test(str);
        boolean b = test1 && test;
        //return b;
        // 相当于上面4行
        return pre.and(pre1).test(str);
    }

    public static void convert(String s, Function<String,Integer> fun){
        int i = fun.apply(s);
        System.out.println(i);
    }

    public static void convert(String s,Function<String,Integer> fun,Function<Integer,String> fun1){
        String apply = fun.andThen(fun1).apply(s);
        System.out.println(apply);
    }

}
