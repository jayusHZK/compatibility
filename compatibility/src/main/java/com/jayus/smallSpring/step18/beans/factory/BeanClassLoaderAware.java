package com.jayus.smallSpring.step18.beans.factory;

/**
 * @author : h zk
 * @date : 2023/8/2 17:21
 * @description :
 **/
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);

}
