package com.jayus.onjava.generic.genericInterface;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/8/9 9:51
 * @description :
 **/
public class CoffeeSupplier implements Supplier<Coffee>, Iterable<Coffee> {

    private Class<?>[] types = {Latte.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class};

    private Random rand = new Random(47);

    public CoffeeSupplier() {
    }

    private int size = 0;

    public CoffeeSupplier(int sz) {
        size = sz;
    }

    class coffeeIterator implements Iterator<Coffee> {
        int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Coffee next() {
            count--;
            return CoffeeSupplier.this.get();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new coffeeIterator();
    }

    @Override
    public Coffee get() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        Stream.generate(new CoffeeSupplier()).limit(5).forEach(System.out::println);
        System.out.println("-----------------");
        for (Coffee coffee : new CoffeeSupplier(5)) {
            System.out.println(coffee);
        }
    }
}
