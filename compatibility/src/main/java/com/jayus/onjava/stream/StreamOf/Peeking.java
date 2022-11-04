package com.jayus.onjava.stream.StreamOf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author : h zk
 * @date : 2022/7/27 15:05
 * @description :
 **/
public class Peeking {
    public static void main(String[] args) throws IOException {
        Files.readAllLines(Paths.get("C:\\Users\\sinozo\\Desktop\\2.txt"))
                .stream()
                // peek操作的目的是帮助调试。它允许你无修改的查看流中的元素
                .peek(s -> System.out.println(s + "1"))
                .limit(4)
                .map(w -> w + " ")
                .peek(System.out::println)
                .map(String::toLowerCase)
                .peek(System.out::println)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
