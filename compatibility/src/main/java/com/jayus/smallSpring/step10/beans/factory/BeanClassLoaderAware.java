package com.jayus.smallSpring.step10.beans.factory;

/**
 * @author : h zk
 * @date : 2023/3/31 15:39
 * @description :
 **/
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);

}
