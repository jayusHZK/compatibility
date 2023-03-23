package com.jayus.smallSpring.step08.beans.factory;

/**
 * @author : h zk
 * @date : 2023/3/23 19:27
 * @description :
 **/
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);

}
