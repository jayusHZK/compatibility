package com.jayus.smallSpring.step04.factory.support;

import com.jayus.smallSpring.step04.BeansException;
import com.jayus.smallSpring.step04.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author: h zk
 * @Description: bean 实例化策略
 * @Date: 2023/3/12 13:49
 * @Version: 1.0
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName,
                       Constructor ctor,Object[] args) throws BeansException;

}