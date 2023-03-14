package com.jayus.smallSpring.step03.factory.support;

import com.jayus.smallSpring.step03.factory.config.BeanDefinition;
import com.jayus.smallSpring.step03.exception.BeanException;

import java.lang.reflect.Constructor;

/**
 * @Author: h zk
 * @Description: bean 实例化策略
 * @Date: 2023/3/10 00:19
 * @Version: 1.0
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args) throws BeanException;

}
