package com.jayus.onjava.stream.optional;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/8/22 15:54
 * @description :
 **/
/*
ifPresent(Consumer)：当值存在时调用 Consumer，否则什么也不做。
orElse(otherObject)：如果值存在则直接返回，否则生成 otherObject。
orElseGet(Supplier)：如果值存在则直接返回，否则使用 Supplier 函数生成一个可替代对象。
orElseThrow(Supplier)：如果值存在直接返回，否则使用 Supplier 函数生成一个异常。
 */
public class Optionals {
    static void basics(Optional<String> optString) {
        if (optString.isPresent())
            System.out.println(optString.get());
        else
            System.out.println("Nothing inside!");
    }

    static void ifPresent(Optional<String> optString) {
        optString.ifPresent(System.out::println);
    }

    static void orElse(Optional<String> optString){
        System.out.println(optString.orElse("Nada"));
    }

    static void orElseGet(Optional<String> optString) {
        System.out.println(optString.orElseGet(() -> "Generated"));
    }

    static void orElseThrow(Optional<String> optString){
        try {
            System.out.println(optString.orElseThrow(()-> new Exception("Supplied")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void test(String testName, Consumer<Optional<String>> cos){
        System.out.println("===" + testName + "===");
        cos.accept(Stream.of("Epithets").findFirst());
        cos.accept(Stream.<String>empty().findFirst());
    }

    public static void main(String[] args) {
        test("basic",Optionals::basics);
        test("ifPresent",Optionals::ifPresent);
        test("orElse",Optionals::orElse);
        test("orElseGet",Optionals::orElseGet);
        test("orElseThrow",Optionals::orElseThrow);
    }

}
