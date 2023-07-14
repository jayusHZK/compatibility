package com.jayus.smallSpring.step16.beans.factory;

/**
 * @author : h zk
 * @date : 2023/7/14 11:24
 * @description :
 **/
public interface BeanNameAware extends Aware{

    void setBeanName(String name);

}
