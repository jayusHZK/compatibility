package com.jayus.smallSpring.step13.beans.factory;

import com.jayus.smallSpring.step13.beans.BeansException;
import com.jayus.smallSpring.step13.beans.factory.config.AutowireCapableBeanFactory;
import com.jayus.smallSpring.step13.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step13.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
