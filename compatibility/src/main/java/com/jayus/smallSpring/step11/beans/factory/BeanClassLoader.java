package com.jayus.smallSpring.step11.beans.factory;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/5 22:00
 * @Version: 1.0
 */
public interface BeanClassLoader extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}
