package com.jayus.smallSpring.step08.beans.factory;


import com.jayus.smallSpring.step08.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/23 19:30
 * @description :
 **/
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
