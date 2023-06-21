package com.jayus.smallSpring.step14.beans.factory.support;

import com.jayus.smallSpring.step14.beans.BeansException;
import com.jayus.smallSpring.step14.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author : h zk
 * @date : 2023/6/21 15:27
 * @description :
 **/
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args) throws BeansException;

}
