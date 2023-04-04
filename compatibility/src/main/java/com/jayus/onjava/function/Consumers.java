package com.jayus.onjava.function;


import java.util.Objects;
import java.util.function.Consumer;

public class Consumers {

    static public <T> void with(Boolean expresion, Consumer<T> consumer, T t) {
        if (expresion && Objects.nonNull(consumer)) {
            consumer.accept(t);
        }
    }

    static public <T,R> void with(Boolean expresion, Consumer<T> trueConsumer, T trueParam, Consumer<R> falseConsumer, R falseParam) {
        if (expresion && Objects.nonNull(trueConsumer)) {
            trueConsumer.accept(trueParam);
        } else if (!expresion && Objects.nonNull(falseConsumer)) {
            falseConsumer.accept(falseParam);
        }
    }

    static public <T> void with(Boolean expresion, Consumer<T> consumer, T t1, T t2) {
        with(expresion, consumer, t1,consumer,t2);
    }
}