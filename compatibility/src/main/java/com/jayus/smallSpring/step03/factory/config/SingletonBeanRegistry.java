package com.jayus.smallSpring.step03.factory.config;

/**
 * @Author: h zk
 * @Description: 单例bean注册器
 * @Date: 2023/3/10 00:14
 * @Version: 1.0
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}