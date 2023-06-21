package com.jayus.smallSpring.step14.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/6/21 14:30
 * @description :
 **/
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName,Object singletonObject);

}
