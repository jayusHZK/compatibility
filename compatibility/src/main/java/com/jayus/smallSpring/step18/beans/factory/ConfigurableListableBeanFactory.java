package com.jayus.smallSpring.step18.beans.factory;

import com.jayus.smallSpring.step18.beans.BeansException;
import com.jayus.smallSpring.step18.beans.factory.config.AutowireCapableBeanFactory;
import com.jayus.smallSpring.step18.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step18.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/8/2 17:28
 * @description :
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
