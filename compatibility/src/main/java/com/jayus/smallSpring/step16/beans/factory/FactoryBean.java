package com.jayus.smallSpring.step16.beans.factory;

/**
 * @author : h zk
 * @date : 2023/7/14 11:25
 * @description :
 **/
public interface FactoryBean <T>{

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
