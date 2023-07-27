package com.jayus.smallSpring.step17.beans.factory;

import com.jayus.smallSpring.step16.aop.ClassFilter;

/**
 * @author : h zk
 * @date : 2023/7/27 11:19
 * @description :
 **/
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
