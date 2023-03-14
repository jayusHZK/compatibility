package com.jayus.smallSpring.step03.factory.support;

import com.jayus.smallSpring.step03.factory.config.BeanDefinition;

/**
 * @Author: h zk
 * @Description: bean 定义注册器
 * @Date: 2023/3/10 00:17
 * @Version: 1.0
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String name, BeanDefinition beanDefinition);

}