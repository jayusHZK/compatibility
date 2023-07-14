package com.jayus.smallSpring.step16.beans.factory.support;

import com.jayus.smallSpring.step16.beans.BeansException;
import com.jayus.smallSpring.step16.beans.factory.config.BeanDefinition;

/**
 * @author : h zk
 * @date : 2023/7/14 14:33
 * @description :
 **/
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();

}
