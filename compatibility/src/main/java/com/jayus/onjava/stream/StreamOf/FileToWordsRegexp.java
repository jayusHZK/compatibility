package com.jayus.onjava.stream.StreamOf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/27 14:42
 * @description :
 **/
public class FileToWordsRegexp {
    private String all;

    public FileToWordsRegexp() throws IOException {
        all = Files.lines(Paths.get("C:\\Users\\sinozo\\Desktop\\2.txt"))
                .skip(1)
                .collect(Collectors.joining());
    }

    public Stream<String> stream(){
        return Pattern
                .compile("[ .?,]").splitAsStream(all);
    }

    public static void main(String[] args) throws IOException {
        FileToWordsRegexp fileToWordsRegexp = new FileToWordsRegexp();
        fileToWordsRegexp.stream()
                .limit(7)
                .map(w -> w+" ")
                .forEach(System.out::println);

    }
}
