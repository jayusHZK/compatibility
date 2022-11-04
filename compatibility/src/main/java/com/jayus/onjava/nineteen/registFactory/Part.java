package com.jayus.onjava.nineteen.registFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/8/3 17:39
 * @description :
 **/
public class Part implements Supplier<String> {

    public Part() {
        System.out.println("part");
        System.out.println(getClass().getSimpleName());
    }

    static List<Supplier<? extends Part>> prototypes =
            Arrays.asList();

    @Override
    public String get() {
        return null;
    }

    public static void main(String[] args) {
        Stream.generate(new Part()).limit(10).forEach(System.out::println);
        try {
            Class<?> aa = Class.forName("aa");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
