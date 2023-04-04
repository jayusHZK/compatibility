package com.jayus.onjava.nineteen.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author : h zk
 * @date : 2022/8/3 18:07
 * @description :
 **/
public class ShowMethods {

    static int a = 0;
    final int b = 1;
    int c = 3;
    static final int d = 4;

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.jayus.onjava.nineteen.reflect.ShowMethods");
        Field[] fields = aClass.getDeclaredFields();
        System.out.println(fields.length);
        for (Field item : fields) {
            System.out.println("当前字段名为"+item.getName()+",类型为:"+item.getType()+",是否为 Final:"+Modifier.isFinal(item.getModifiers())
            +",是否为 static:"+Modifier.isStatic(item.getModifiers()));
        }
        System.out.println("field end-------");
        Method[] methods = aClass.getMethods();
        for (Method item : methods) {
            System.out.println("当前方法名为:" + item.getName() +",返回值为:"+item.getReturnType() +",是否是 final:"+Modifier.isFinal(item.getModifiers())
            +",入参个数为:"+item.getParameterCount()+",入参类型为:" + Arrays.asList(item.getParameterTypes())+item.getModifiers());
        }
    }
}
