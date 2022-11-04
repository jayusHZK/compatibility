package com.jayus.onjava.stream.optional;

import java.util.Optional;

/**
 * @author : h zk
 * @date : 2022/8/22 16:10
 * @description :
 **/
public class CreatingOptionals {
    static void test(String testName, Optional<String> opt){
        System.out.println("===" + testName + "===");
        System.out.println(opt.orElse("Null"));
    }

    public static void main(String[] args) {
        test("empty",Optional.empty());
        test("of",Optional.of("Howdy"));
        try {
            test("of",Optional.of(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        test("ofNullable",Optional.ofNullable("Hi"));
        test("OfNullable",Optional.ofNullable(null));
    }
}
