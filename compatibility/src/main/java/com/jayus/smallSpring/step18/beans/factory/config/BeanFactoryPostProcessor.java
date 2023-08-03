package com.jayus.smallSpring.step18.beans.factory.config;

import com.jayus.smallSpring.step18.beans.BeansException;
import com.jayus.smallSpring.step18.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/8/3 18:11
 * @description :
 **/
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
