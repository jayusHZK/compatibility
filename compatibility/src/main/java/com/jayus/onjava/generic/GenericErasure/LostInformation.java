package com.jayus.onjava.generic.GenericErasure;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author : h zk
 * @date : 2022/8/10 10:15
 * @description :
 **/
public class LostInformation {

    public static void main(String[] args) {
        // [E]
        System.out.println(Arrays.asList(new ArrayList().getClass().getTypeParameters()));
        // [class [Ljava.lang.reflect.TypeVariable;]
        System.out.println(Arrays.asList(new ArrayList().getClass().getTypeParameters().getClass()));
    }
}
