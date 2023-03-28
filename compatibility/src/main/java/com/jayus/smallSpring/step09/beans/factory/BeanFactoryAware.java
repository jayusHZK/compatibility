package com.jayus.smallSpring.step09.beans.factory;

import com.jayus.smallSpring.step09.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/28 10:49
 * @description :
 **/
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
