package com.jayus.smallSpring.step18.beans.factory;

import com.jayus.smallSpring.step18.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/8/2 17:26
 * @description :
 **/
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
