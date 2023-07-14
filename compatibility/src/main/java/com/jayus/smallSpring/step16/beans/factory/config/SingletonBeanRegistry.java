package com.jayus.smallSpring.step16.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/7/14 11:38
 * @description :
 **/
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName,Object singletonObject);

}
