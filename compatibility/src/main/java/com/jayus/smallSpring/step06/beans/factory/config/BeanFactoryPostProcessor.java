package com.jayus.smallSpring.step06.beans.factory.config;

import com.jayus.smallSpring.step06.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/14 17:49
 * @description :
 **/
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableBeanFactory beanFactory) throws BeansException;

}
