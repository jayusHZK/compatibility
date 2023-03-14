package com.jayus.smallSpring.step04.factory.config;

/**
 * @Author: h zk
 * @Description: 单例注册表
 * @Date: 2023/3/12 13:47
 * @Version: 1.0
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}