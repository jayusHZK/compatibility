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

    FilledList(Supplier<? extends List> factory,Supplier<T> gen,int size){
        Suppliers.create(factory,gen,size);
    }

    public static void main(String[] args) {
        List<String> list = new FilledList<>("Hello", 4);
        System.out.println(list);
        System.out.println("----------");
        FilledList<Integer> ilist = new FilledList<>(() -> 47, 4);
        ilist.forEach(System.out::println);
        System.out.println("----------");
        List<Integer> factoryList = new ArrayList<>();
        Suppliers.create(() -> factoryList, () -> 50, 6);
        factoryList.forEach(System.out::println);
    }
}
