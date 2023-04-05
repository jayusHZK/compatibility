package com.jayus.smallSpring.step11.beans.factory.config;

/**
 * @Author: h zk
 * @Description: 单例注册表
 * @Date: 2023/4/5 22:13
 * @Version: 1.0
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName,Object singletonObject);

}
