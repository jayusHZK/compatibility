package com.jayus.onjava.stream.optional;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/27 17:21
 * @description :
 **/
public class OptionalsFromEmptyStreams {
    public static void main(String[] args) {
        String o = Optional.ofNullable("").orElse(new String());
        // findFirst() 返回一个包含第一个元素的 Optional 对象，如果流为空则返回 Optional.empty
        System.out.println(Stream.<String>empty().findFirst());
        // findAny() 返回包含任意元素的 Optional 对象，如果流为空则返回 Optional.empty
        System.out.println(Stream.<String>empty().findAny());
        // max() 和 min() 返回一个包含最大值或者最小值的 Optional 对象，如果流为空则返回 Optional.empty
        System.out.println(Stream.<String>empty().max(String.CASE_INSENSITIVE_ORDER));
        // reduce() 不再以 identity 形式开头，而是将其返回值包装在 Optional 中。（identity 对象成为其他形式的 reduce() 的默认结果，因此不存在空结果的风险）
        System.out.println(Stream.<String>empty().reduce((s1, s2) -> s1 + s2));
        System.out.println(IntStream.empty().average());
    }
}
