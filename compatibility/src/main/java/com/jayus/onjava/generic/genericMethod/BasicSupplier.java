package com.jayus.onjava.generic.genericMethod;

import java.util.function.Supplier;

/**
 * @author : h zk
 * @date : 2022/8/9 16:24
 * @description :
 **/
public class BasicSupplier<T> implements Supplier<T> {
    private Class<T> type;

    public BasicSupplier(Class<T> type){
        this.type = type;
    }

    @Override
    public T get() {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static <T>Supplier<T> create(Class<T> type){
        return new BasicSupplier<>(type);
    }

}

