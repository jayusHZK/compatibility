package com.jayus.smallSpring.step15.beans.factory;

/**
 * @author : h zk
 * @date : 2023/7/5 18:27
 * @description :
 **/
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
