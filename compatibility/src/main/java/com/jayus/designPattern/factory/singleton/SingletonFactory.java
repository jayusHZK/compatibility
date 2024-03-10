package com.jayus.designPattern.factory.singleton;

import java.lang.reflect.Constructor;

/**
 * 单例工厂
 */
public class SingletonFactory {

    private static Singleton singleton;

    static {
        try {
            Class<?> cl = Class.forName(Singleton.class.getName());
            Constructor<?> constructor = cl.getDeclaredConstructor();
            // 设置无参构造可访问
            constructor.setAccessible(true);
            singleton = (Singleton) constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Singleton getSingleton(){
        return singleton;
    }

}
