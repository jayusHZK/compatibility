package com.jayus.smallSpring.step09.beans.factory;

/**
 * @author : h zk
 * @date : 2023/3/28 11:01
 * @description :
 **/
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}
