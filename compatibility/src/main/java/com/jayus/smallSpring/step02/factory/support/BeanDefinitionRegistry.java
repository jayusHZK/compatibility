package com.jayus.smallSpring.step02.factory.support;

import com.jayus.smallSpring.step02.factory.config.BeanDefinition;

/**
 * @Author: h zk
 * @Description: 向注册表中注册 BeanDefinition
 * @Date: 2023/3/8 22:54
 * @Version: 1.0
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}