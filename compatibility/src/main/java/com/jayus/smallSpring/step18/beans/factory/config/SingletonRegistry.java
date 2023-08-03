package com.jayus.smallSpring.step18.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/8/3 18:07
 * @description :
 **/
public interface SingletonRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName,Object singletonObject);

}
