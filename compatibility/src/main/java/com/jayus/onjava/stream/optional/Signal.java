package com.jayus.onjava.stream.optional;

import org.bouncycastle.asn1.esf.SPuri;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/7/28 11:45
 * @description :
 **/
public class Signal {
    private final String msg;

    public Signal(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return "Signal{" +
                "msg='" + msg + '\'' +
                '}';
    }

    static Random rand = new Random(47);

    public static Signal morse() {
        System.out.println(1);
        switch (rand.nextInt(4)) {
            case 1:
                return new Signal("dot");
            case 2:
                return new Signal("dash");
            default:
                return new Signal("a");
        }
    }

    public static Stream<Optional<Signal>> stream() {
        return Stream.generate(Signal::morse)
                .map(signal -> Optional.ofNullable(signal));
    }

    public static void main(String[] args) {
        stream().forEach(item -> System.out.println(item.get()));
        //Stream.generate(Signal::morse).limit()
    }
}
