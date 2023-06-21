package com.jayus.smallSpring.step14.beans.factory;

/**
 * @author : h zk
 * @date : 2023/6/21 13:50
 * @description :
 **/
public interface BeanNameAware extends Aware{

    void setBeanName(String name);

}
