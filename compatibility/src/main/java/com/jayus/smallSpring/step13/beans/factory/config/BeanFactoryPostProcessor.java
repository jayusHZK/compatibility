package com.jayus.smallSpring.step13.beans.factory.config;

import com.jayus.smallSpring.step13.beans.BeansException;
import com.jayus.smallSpring.step13.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
