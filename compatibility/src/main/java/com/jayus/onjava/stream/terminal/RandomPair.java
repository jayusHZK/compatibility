package com.jayus.onjava.stream.terminal;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/8/22 17:09
 * @description :
 **/
public class RandomPair {
    Random rand = new Random(47);

    Iterator<Character> capChars = rand.ints(65,91)
            .mapToObj( i -> (char)i)
            .iterator();

    public Stream<Pair> stream(){
        return rand.ints(100,1000).distinct()
                .mapToObj(i -> new Pair(capChars.next(),i));
    }

    public static void main(String[] args) {
        Map<Integer,Character> map = new RandomPair().stream().limit(8).collect(Collectors.toMap(Pair::getI,Pair::getC));
        System.out.println(map);
    }
}
