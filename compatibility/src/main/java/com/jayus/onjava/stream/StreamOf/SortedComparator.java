package com.jayus.onjava.stream.StreamOf;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author : h zk
 * @date : 2022/7/27 15:16
 * @description :
 **/
public class SortedComparator {
    public static void main(String[] args) throws Exception {
        System.out.println(6.2/0);
        List<String> a = null;
        Optional.ofNullable(a).orElse(Collections.emptyList()).forEach(item ->{

        });

        Files.readAllLines(Paths.get("C:\\Users\\sinozo\\Desktop\\2.txt"))
                .stream()
                .skip(1)
                .limit(4)
                .sorted(Comparator.reverseOrder())
                .map(w -> w + " ")
                .forEach(System.out::println);
    }
}
