package com.jayus.smallSpring.step18.beans.factory.support;

import com.jayus.smallSpring.step18.beans.BeansException;
import com.jayus.smallSpring.step18.beans.factory.config.BeanDefinition;

/**
 * @author : h zk
 * @date : 2023/8/4 10:10
 * @description :
 **/
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();

}
