package com.jayus.smallSpring.step17.beans.factory.support;

import com.jayus.smallSpring.step17.beans.BeansException;
import com.jayus.smallSpring.step17.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author : h zk
 * @date : 2023/7/28 10:15
 * @description :
 **/
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args) throws BeansException;

}
