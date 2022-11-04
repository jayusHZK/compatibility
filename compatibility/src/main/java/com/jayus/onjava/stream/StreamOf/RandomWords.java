package com.jayus.onjava.stream.StreamOf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/26 17:48
 * @description :
 **/
public class RandomWords implements Supplier<String> {

    List<String> words = new ArrayList<>();

    Random random = new Random(47);

    RandomWords() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\sinozo\\Desktop\\2.txt"));
        for (String line : lines.subList(1,lines.size())) {
            for (String word : line.split("[ .?,]+")) {
                System.out.println(word);
                words.add(word.toLowerCase());
            }
        }
    }

    @Override
    public String get() {
        return words.get(random.nextInt(words.size()));
    }

    @Override
    public String toString() {
        return words.stream()
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) throws IOException {
        String a = "Not much of a cheese shop really, is it?";
        String[] split = a.split("[ .?,]+");
        System.out.println(split[0]);
        if (1 == 1) return;
        List<String> list = new ArrayList<>();
        //Stream.generate() 的用法，它可以把任意 Supplier<T> 用于生成 T 类型的流
        System.out.println(Stream.generate(new RandomWords()).limit(10)
        .collect(Collectors.joining(" ")));
    }

}
