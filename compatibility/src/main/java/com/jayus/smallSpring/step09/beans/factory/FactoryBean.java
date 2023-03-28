package com.jayus.smallSpring.step09.beans.factory;

import com.jayus.smallSpring.step09.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/28 10:39
 * @description :
 **/
public interface FactoryBean<T> {

    T getObject() throws BeansException;

    Class<?> getObjectType();

    boolean isSingleton();

}
