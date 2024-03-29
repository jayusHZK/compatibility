package com.jayus.smallSpring.step15.beans.factory.config;

import com.jayus.smallSpring.step15.beans.BeansException;
import com.jayus.smallSpring.step15.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/7/6 11:47
 * @description :
 **/
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
