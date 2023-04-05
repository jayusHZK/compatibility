package com.jayus.smallSpring.step11.beans.factory;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/5 22:01
 * @Version: 1.0
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
