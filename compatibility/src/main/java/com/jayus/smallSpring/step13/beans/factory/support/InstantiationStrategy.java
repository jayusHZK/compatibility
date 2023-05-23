package com.jayus.smallSpring.step13.beans.factory.support;

import com.jayus.smallSpring.step13.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args) throws Exception;

}
