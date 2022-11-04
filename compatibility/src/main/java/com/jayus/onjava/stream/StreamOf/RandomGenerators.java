package com.jayus.onjava.stream.StreamOf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/26 17:36
 * @description :
 **/
public class RandomGenerators {

    static List<Object> list = new ArrayList<>();

    public static <T> void show(Stream<T> stream) {
        stream
                .limit(4)
                //.forEach(list::add);
                .forEach(System.out::println);
        System.out.println("------------------------------");
    }

    public static void main(String[] args) throws IOException {
        Random random = new Random(47);
        /*
        boxed() 流操作将会自动地把基本类型包装成为对应的装箱类型，从而使得 show() 能够接受流。
         */
        show(random.ints().boxed());
        List<String> strings = Files.readAllLines(Paths.get("C:\\Users\\sinozo\\Desktop\\xhshtml.txt"));
        System.out.println(strings.get(0));

    }

}
