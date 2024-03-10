package com.jayus.spring.abstractTest;

/**
 * @author : h zk
 * @date : 2022/11/24 16:48
 * @description :
 **/
public abstract class SonAbstractClass extends abstractClass{

    @Override
    protected void refresh() {
        System.out.println("触发抽象类子类实现");
    }

}
