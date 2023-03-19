package com.jayus.smallSpring.step07.beans.factory;

import com.jayus.smallSpring.step07.beans.BeansException;
import com.jayus.smallSpring.step07.beans.factory.config.AutowireCapableBeanFactory;
import com.jayus.smallSpring.step07.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step07.beans.factory.config.ConfigurableBeanFactory;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/17 23:33
 * @Version: 1.0
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
