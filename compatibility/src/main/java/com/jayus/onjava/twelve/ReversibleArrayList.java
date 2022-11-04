package com.jayus.onjava.twelve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author : h zk
 * @date : 2022/8/8 16:53
 * @description :
 **/
public class ReversibleArrayList<T> extends ArrayList<T> {
    ReversibleArrayList(Collection<T> c) {
        super(c);
    }

    public Iterable<T> reversed() {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    int current = size() - 1;

                    @Override
                    public boolean hasNext() {
                        return current > -1;
                    }

                    @Override
                    public T next() {
                        return get(current--);
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public static void main(String[] args) {
        ReversibleArrayList<String> list = new ReversibleArrayList(Arrays.asList("to be con you are go".split(" ")));
        for (String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (String s : list.reversed()) {
            System.out.print(s + " ");
        }
    }
}
