package com.jayus.designPattern.singleton;

/**
 * 双重检查单例
 */
public class Singleton {

    private volatile static Singleton singleton;


    public Singleton getInstance() {
        if (singleton == null) { // 判断为 null 才获取锁
            synchronized (Singleton.class) {
                if (singleton == null) { // 防止变量实例化之后 后面的线程进来又实例化一次
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
