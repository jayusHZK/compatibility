package com.jayus.onjava.twelve;

import java.util.Iterator;

/**
 * @author : h zk
 * @date : 2022/8/8 17:20
 * @description :
 **/
public class IterableClass implements Iterable<String>{

    protected String[] words = ("can you speak english ? do you").split(" ");

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() {
                return words[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        for (String s : new IterableClass()) {
            System.out.println(s +" ");
        }
    }
}
