package com.jayus.smallSpring.step14.beans.factory;

import com.jayus.smallSpring.step14.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/6/21 13:49
 * @description :
 **/
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
