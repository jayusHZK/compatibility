package com.jayus.onjava.generic.GenericErasure;

import com.jayus.onjava.generic.Suppliers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author : h zk
 * @date : 2022/8/10 14:51
 * @description :
 **/
public class FilledList<T> extends ArrayList<T> {
    FilledList(Supplier<T> gen,int size){
        Suppliers.fill(this,gen,size);
    }

    public FilledList(T t,int size) {
        for (int i = 0; i < size; i++) {
            this.add(t);
        }
    }

    public static void main(String[] args) {
        List<String> list = new FilledList<>("Hello", 4);
        System.out.println(list);
        FilledList<Integer> ilist = new FilledList<>(() -> 47, 4);

    }
}
