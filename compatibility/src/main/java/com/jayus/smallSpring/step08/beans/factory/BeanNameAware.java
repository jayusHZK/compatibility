package com.jayus.smallSpring.step08.beans.factory;

/**
 * @author : h zk
 * @date : 2023/3/23 19:32
 * @description :
 **/
public interface BeanNameAware extends Aware{

    void setBeanName(String name);

}
