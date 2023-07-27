package com.jayus.smallSpring.step17.beans.factory;

import com.jayus.smallSpring.step17.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/7/27 11:17
 * @description :
 **/
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
