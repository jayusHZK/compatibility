package com.jayus.smallSpring.step15.beans.factory;

/**
 * @author : h zk
 * @date : 2023/7/5 18:26
 * @description :
 **/
public interface BeanNameAware extends Aware{

    void setBeanName(String name);

}
