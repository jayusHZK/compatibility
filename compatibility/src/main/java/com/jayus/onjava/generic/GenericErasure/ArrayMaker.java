package com.jayus.onjava.generic.GenericErasure;

import java.lang.reflect.Array;

/**
 * @author : h zk
 * @date : 2022/8/10 14:44
 * @description :
 **/
public class ArrayMaker<T> {
    private Class<T> kind;

    public ArrayMaker(Class<T> kind){
        this.kind = kind;
    }

    T[] create(int size){
        System.out.println(kind.getSimpleName());
        return (T[]) Array.newInstance(kind,size);
    }

    public static void main(String[] args) {
        ArrayMaker<String> stringArrayMaker = new ArrayMaker<>(String.class);
        String[] strings = stringArrayMaker.create(8);
        System.out.println(strings[0].getClass().getSimpleName());
    }
}
