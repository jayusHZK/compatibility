package com.jayus.smallSpring.step04.factory.support;

import com.jayus.smallSpring.step04.factory.config.BeanDefinition;

/**
 * @Author: h zk
 * @Description: bean定义注册表
 * @Date: 2023/3/12 14:16
 * @Version: 1.0
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}