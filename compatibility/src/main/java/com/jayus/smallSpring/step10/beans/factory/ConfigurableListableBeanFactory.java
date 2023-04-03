package com.jayus.smallSpring.step10.beans.factory;

import com.jayus.smallSpring.step10.beans.BeansException;
import com.jayus.smallSpring.step10.beans.factory.config.AutowireCapableBeanFactory;
import com.jayus.smallSpring.step10.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step10.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/4/3 11:34
 * @description :
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory , AutowireCapableBeanFactory
        , ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName)  throws BeansException;

    void preInstantiateSingleton() throws BeansException;

}
