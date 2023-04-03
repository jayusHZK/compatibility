package com.jayus.smallSpring.step10.beans.factory.config;

import com.jayus.smallSpring.step10.beans.BeansException;
import com.jayus.smallSpring.step10.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/4/3 13:47
 * @description :
 **/
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
