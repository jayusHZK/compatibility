package com.jayus.smallSpring.step15.beans.factory;

import com.jayus.smallSpring.step15.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/7/5 18:25
 * @description :
 **/
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
