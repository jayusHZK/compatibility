package com.jayus.smallSpring.step11.beans.factory;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/5 21:59
 * @Version: 1.0
 */
public interface BeanNameAware extends Aware{

    void setBeanName(String name);

}