package com.jayus.smallSpring.step09.beans.factory;

/**
 * @author : h zk
 * @date : 2023/3/28 10:48
 * @description :
 **/
public interface BeanNameAware extends Aware{

    void setBeanName(String name);

}
