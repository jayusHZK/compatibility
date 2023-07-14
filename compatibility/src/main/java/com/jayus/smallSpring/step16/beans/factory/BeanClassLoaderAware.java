package com.jayus.smallSpring.step16.beans.factory;

/**
 * @author : h zk
 * @date : 2023/7/14 11:16
 * @description :
 **/
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);

}
