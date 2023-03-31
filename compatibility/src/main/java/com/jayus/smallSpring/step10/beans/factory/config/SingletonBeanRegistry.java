package com.jayus.smallSpring.step10.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/3/31 15:43
 * @description :
 **/
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName,Object singletonObject);

}
