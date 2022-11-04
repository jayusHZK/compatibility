package com.jayus.onjava.stream.collectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author : h zk
 * @date : 2022/7/28 15:46
 * @description :
 **/
public class SepcialCollector {
    public static void main(String[] args) throws IOException {
        ArrayList<Object> words = Files.readAllLines(Paths.get("C:\\Users\\sinozo\\Desktop\\2.txt"))
                .stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        words.stream().filter(s -> s.equals("cheese")).forEach(System.out::println);

    }
}
