package com.jayus.onjava.stream.collectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author : h zk
 * @date : 2022/7/28 17:43
 * @description :
 **/
public class Informational {
    public static void main(String[] args) throws IOException {
        System.out.println(
                Files.readAllLines(Paths.get("C:\\Users\\sinozo\\Desktop\\2.txt")).stream().count()
        );

        System.out.println(
                Files.readAllLines(Paths.get("C:\\Users\\sinozo\\Desktop\\2.txt")).stream().min(String.CASE_INSENSITIVE_ORDER).orElse("None")
        );

        System.out.println(
                Files.readAllLines(Paths.get("C:\\Users\\sinozo\\Desktop\\2.txt")).stream().max(String.CASE_INSENSITIVE_ORDER).orElse("None")
        );
    }
}
