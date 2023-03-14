package com.jayus.smallSpring.step05.beans.factory.support;

import com.jayus.smallSpring.step05.beans.BeansException;
import com.jayus.smallSpring.step05.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/12 20:54
 * @Version: 1.0
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String name, Constructor ctor,Object[] args) throws BeansException;

}