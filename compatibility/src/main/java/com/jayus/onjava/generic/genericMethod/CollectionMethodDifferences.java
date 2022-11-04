package com.jayus.onjava.generic.genericMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author : h zk
 * @date : 2022/8/9 18:15
 * @description :
 **/
public class CollectionMethodDifferences {
    static Set<String> methodSet(Class<?> type) {
        return Arrays.stream(type.getMethods())
                .map(Method::getName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    static void interfaces(Class<?> type) {
        System.out.print("Interfaces in " + type.getSimpleName() + ": ");
        System.out.println(Arrays.stream(type.getInterfaces()).map(Class::getSimpleName).collect(Collectors.toList()));
    }

    static Set<String> object = methodSet(Object.class);

    static {
        object.add("clone");
    }

    static void difference(Class<?> superset,Class<?> subset){
        System.out.print(superset.getSimpleName() + " extends " + subset.getSimpleName() + ", adds: ");
        Set<String> comp = Sets.difference(methodSet(superset),methodSet(subset));
        comp.removeAll(object);
        System.out.println(comp);
        interfaces(superset);
    }

    public static void main(String[] args) {
        System.out.println("Collection: " + methodSet(Collection.class));
        interfaces(Collection.class);
        difference(Set.class,Collection.class);
    }
}
