package com.jayus.jvm.classLoaderTest;

import java.lang.reflect.Method;

public class test {

    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader("D:\\idea\\ccc\\compatibility\\compatibility\\src\\main\\java\\com\\jayus\\jvm\\classLoaderTest\\");
        Class<?> hello = myClassLoader.loadClass("Hello");
        Object o = hello.newInstance();
        Method sayHello = hello.getMethod("sayHello", null);
        sayHello.invoke(o);
    }

}
