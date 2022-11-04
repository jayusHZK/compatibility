package com.jayus.onjava.stream.collectors.match;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/28 16:06
 * @description :
 **/
public interface Matcher extends BiPredicate<Stream<Integer>, Predicate<Integer>> {
}
