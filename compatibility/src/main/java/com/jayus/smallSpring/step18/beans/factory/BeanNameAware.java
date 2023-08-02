package com.jayus.smallSpring.step18.beans.factory;

/**
 * @author : h zk
 * @date : 2023/8/2 17:27
 * @description :
 **/
public interface BeanNameAware extends Aware {

    void setBeanName(String name);

}
