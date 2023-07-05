package com.jayus.smallSpring.step15.beans.factory;

/**
 * @author : h zk
 * @date : 2023/7/5 18:21
 * @description :
 **/
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}
