package com.jayus.smallSpring.step09.beans.factory.config;

/**
 * @author : h zk
 * @date : 2023/3/28 11:08
 * @description :
 **/
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
