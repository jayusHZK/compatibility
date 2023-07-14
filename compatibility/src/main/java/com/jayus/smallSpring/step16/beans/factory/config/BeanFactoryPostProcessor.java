package com.jayus.smallSpring.step16.beans.factory.config;

import com.jayus.smallSpring.step16.beans.BeansException;
import com.jayus.smallSpring.step16.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/7/14 14:29
 * @description :
 **/
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
