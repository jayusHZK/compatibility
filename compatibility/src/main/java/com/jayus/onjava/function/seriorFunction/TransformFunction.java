package com.jayus.onjava.function.seriorFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author : h zk
 * @date : 2022/7/21 11:32
 * @description :
 **/
public class TransformFunction {
    static Function<I,O> transform(Function<I,O> in){
        return in.andThen(o -> {
            System.out.println(o);
            return o;
        });
    }

    public static void main(String[] args) {
        Function<I, O> f1 = transform(i -> {
            System.out.println(i);
            return new O();
        });
        f1.apply(new I());
        final List<String> list = new ArrayList<>();
        list.add("as");
        System.out.println(list.get(0));
    }
}
