package com.jayus.designPattern.factory.singleton;

public class Singleton {

    // 无参构造 禁止实例化
    private Singleton(){}

    public String sayHello(String name){
        return "Hello!"+name;
    }

}
