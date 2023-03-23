package com.jayus.smallSpring.step08.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/3/23 19:40
 * @description :
 **/
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
