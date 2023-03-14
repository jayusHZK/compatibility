package com.jayus.smallSpring.step05.beans.factory.config;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/12 20:43
 * @Version: 1.0
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
