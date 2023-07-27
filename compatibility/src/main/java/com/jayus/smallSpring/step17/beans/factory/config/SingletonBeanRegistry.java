package com.jayus.smallSpring.step17.beans.factory.config;

import com.jayus.smallSpring.step17.beans.factory.ObjectFactory;

/**
 * @author : h zk
 * @date : 2023/7/27 11:29
 * @description :
 **/
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName,Object singletonObject);

}
