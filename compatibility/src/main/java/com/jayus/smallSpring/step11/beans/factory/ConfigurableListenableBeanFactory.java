package com.jayus.smallSpring.step11.beans.factory;

import com.jayus.smallSpring.step11.beans.BeansException;
import com.jayus.smallSpring.step11.beans.factory.config.AutowireCapableBeanFactory;
import com.jayus.smallSpring.step11.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step11.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/4/6 14:41
 * @description :
 **/
public interface ConfigurableListenableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
