package com.jayus.smallSpring.step07.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/3/17 15:01
 * @description :
 **/
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void destorySingletons();

}
