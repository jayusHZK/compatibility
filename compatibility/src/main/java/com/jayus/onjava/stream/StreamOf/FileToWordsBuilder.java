package com.jayus.onjava.stream.StreamOf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/27 14:01
 * @description :
 **/
/*
在建造者模式（Builder design pattern）中，首先创建一个 builder 对象，然后将创建流所需的多个信息传递给它，最后builder 对象执行”创建“流的操作。Stream 库提供了这样的 Builder
 */
public class FileToWordsBuilder {
    Stream.Builder<String> builder = Stream.builder();

    public FileToWordsBuilder() throws IOException {
        Files.readAllLines(Paths.get("C:\\Users\\sinozo\\Desktop\\2.txt"))
                .stream().skip(1)
                .forEach(line -> {
                    for (String word : line.split("[ .?,]"))
                        builder.add(word);
                });
    }

    Stream<String> stream(){
        return builder.build();
    }

    public static void main(String[] args) throws IOException {
        new FileToWordsBuilder()
                .stream()
                .limit(7)
                .map( w -> w+ " ")
                .forEach(System.out::print);
    }
}
