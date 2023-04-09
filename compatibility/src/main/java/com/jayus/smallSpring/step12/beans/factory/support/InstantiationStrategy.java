package com.jayus.smallSpring.step12.beans.factory.support;

import com.jayus.smallSpring.step12.beans.BeansException;
import com.jayus.smallSpring.step12.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 19:24
 * @Version: 1.0
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args) throws BeansException;

}
