package com.jayus.smallSpring.step14.beans.factory;

/**
 * @author : h zk
 * @date : 2023/6/21 11:42
 * @description :
 **/
public interface BeanClassLoaderAware {

    void setBeanClassLoader(ClassLoader classLoader);

}
