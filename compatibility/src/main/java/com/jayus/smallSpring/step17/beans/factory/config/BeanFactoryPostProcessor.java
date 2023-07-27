package com.jayus.smallSpring.step17.beans.factory.config;

import com.jayus.smallSpring.step17.beans.BeansException;
import com.jayus.smallSpring.step17.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/7/27 18:21
 * @description :
 **/
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
