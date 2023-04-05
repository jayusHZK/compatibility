package com.jayus.smallSpring.step11.beans.factory.support;

import com.jayus.smallSpring.step11.beans.BeansException;
import com.jayus.smallSpring.step11.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/5 22:34
 * @Version: 1.0
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args) throws BeansException;

}
