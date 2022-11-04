package com.jayus.onjava.nineteen.optional;

import java.util.Optional;

/**
 * @author : h zk
 * @date : 2022/8/5 15:12
 * @description :
 **/
public class Person {
    public final Optional<String> first;
    public final Optional<String> last;
    public final Optional<String> address;
    public final Boolean empty;

    Person(String first,String last,String address) {
        this.first = Optional.ofNullable(first);
        this.last = Optional.ofNullable(last);
        this.address = Optional.ofNullable(address);
        empty = !this.first.isPresent()
                && !this.last.isPresent()
                && !this.address.isPresent();
    }

    Person() {
        this(null,null,null);
    }

    @Override
    public String toString() {
        if (empty) return "<Empty>";
        return (first.orElse("") +
                " " + last.orElse("")+
                " " + address.orElse("")).trim();
    }

    public static void main(String[] args) {
        System.out.println(new Person());
    }
}
