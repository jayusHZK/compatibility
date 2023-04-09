package com.jayus.smallSpring.step12.beans.factory;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 18:10
 * @Version: 1.0
 */
public interface BeanClassLoaderAware extends Aware {

    void serBeanClassLoader(ClassLoader classLoader);

}
