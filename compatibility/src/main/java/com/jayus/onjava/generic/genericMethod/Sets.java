package com.jayus.onjava.generic.genericMethod;

//import java.util.EnumSet;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import static com.jayus.onjava.generic.genericMethod.Watercolors.*;

/**
 * @author : h zk
 * @date : 2022/8/9 18:02
 * @description :
 **/
public class Sets {
    public static <T>Set<T> union(Set<T> a,Set<T> b){
        Set<T> result = new HashSet<>(a);
        a.addAll(b);
        return result;
    }

    public static <T>Set<T> intersection(Set<T> a,Set<T> b){
        Set<T> result = new HashSet<>(a);
        a.retainAll(b);
        return result;
    }

    public static <T>Set<T> difference(Set<T> a,Set<T> b){
        Set<T> result = new HashSet<>(a);
        a.removeAll(b);
        return result;
    }

    public static <T> Set<T> complement(Set<T> a,Set<T> b){
        return difference(union(a,b),intersection(a,b));
    }

    public static void main(String[] args) {
        // 提供所要在结果Set中创建的元素范围的第一个元素和最后一个元素
        Set<Watercolors> set1 = EnumSet.range(BRILLIANT_RED,VIRIDIAN_HUE);
        System.out.println(set1.size());
    }
}
