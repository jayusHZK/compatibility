package com.jayus.onjava.stream.optional;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/27 17:31
 * @description :
 **/
/*
ifPresent(Consumer)：当值存在时调用 Consumer，否则什么也不做。
orElse(otherObject)：如果值存在则直接返回，否则生成 otherObject。
orElseGet(Supplier)：如果值存在则直接返回，否则使用 Supplier 函数生成一个可替代对象。
orElseThrow(Supplier)：如果值存在直接返回，否则使用 Supplier 函数生成一个异常。
 */
public class OptionalBasics {
    static void test(Optional<String> optString){
        // isPresent() 检查其中是否包含元素
        if (optString.isPresent())
            System.out.println(optString.get());
        else
            System.out.println("Nothing inside");
    }

    public static void main(String[] args) {
        test(Stream.of("a").findFirst());
        test(Stream.<String>empty().findFirst());
    }
}
