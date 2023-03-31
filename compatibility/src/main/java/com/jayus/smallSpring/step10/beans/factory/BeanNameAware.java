package com.jayus.smallSpring.step10.beans.factory;

/**
 * @author : h zk
 * @date : 2023/3/31 15:28
 * @description :
 **/
public interface BeanNameAware extends Aware{

    void setBeanName(String name);

}
