package com.jayus.smallSpring.step16.beans.factory;

import com.jayus.smallSpring.step16.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/7/14 11:23
 * @description :
 **/
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
