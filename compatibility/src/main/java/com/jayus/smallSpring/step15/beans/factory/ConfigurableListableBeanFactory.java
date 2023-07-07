package com.jayus.smallSpring.step15.beans.factory;

import com.jayus.smallSpring.step15.beans.BeansException;
import com.jayus.smallSpring.step15.beans.factory.config.AutowireCapableBeanFactory;
import com.jayus.smallSpring.step15.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step15.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/7/6 9:48
 * @description :
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory
        , AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
