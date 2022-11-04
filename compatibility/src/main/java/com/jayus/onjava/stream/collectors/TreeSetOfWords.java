package com.jayus.onjava.stream.collectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author : h zk
 * @date : 2022/7/28 14:10
 * @description :
 **/
public class TreeSetOfWords {
    public static void main(String[] args) throws IOException {
        Set<String> words = Files.lines(Paths.get("C:\\Users\\sinozo\\Desktop\\2.txt"))
                .flatMap(s -> Arrays.stream(s.split("\\W+")))
                .filter(s -> !s.matches("\\d+"))
                .map(s -> s.trim())
                .filter(s -> s.length() > 2)
                .limit(100)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(words);
    }
}
