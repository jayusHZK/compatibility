package com.jayus.smallSpring.step15.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/7/6 11:35
 * @description :
 **/
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName,Object singletonObject);

}
