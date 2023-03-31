package com.jayus.smallSpring.step10.beans.factory;

/**
 * @author : h zk
 * @date : 2023/3/31 15:15
 * @description :
 **/
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
