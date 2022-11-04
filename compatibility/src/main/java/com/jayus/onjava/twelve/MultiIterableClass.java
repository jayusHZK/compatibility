package com.jayus.onjava.twelve;

import java.util.*;

/**
 * @author : h zk
 * @date : 2022/8/8 17:34
 * @description :
 **/
public class MultiIterableClass extends IterableClass{
    public Iterable<String> reversed(){
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    int current = words.length -1;
                    @Override
                    public boolean hasNext() {
                        return current > -1;
                    }

                    @Override
                    public String next() {
                        return words[current--];
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public Iterable<String> randomized(){
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                ArrayList<String> shuffled = new ArrayList<>(Arrays.asList(words));
                Collections.shuffle(shuffled,new Random(47));
                return shuffled.iterator();
            }
        };
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(new Integer[]{1, 2, 3, 4, 5});
        Collections.shuffle(integers,new Random(2));
        System.out.println(integers);
        MultiIterableClass strings = new MultiIterableClass();
        for (String s : strings.reversed()) {
            System.out.print( s +" ");
        }
        System.out.println();
        for (String s : strings.randomized()) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (String string : strings) {
            System.out.print(string + " ");
        }
    }
}
