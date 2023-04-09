package com.jayus.smallSpring.step12.beans.factory.config;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 18:18
 * @Version: 1.0
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName,Object singletonObject);

}