package com.jayus.smallSpring.step18.beans.factory.support;

import com.jayus.smallSpring.step18.beans.BeansException;
import com.jayus.smallSpring.step18.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author : h zk
 * @date : 2023/8/4 10:14
 * @description :
 **/
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args) throws BeansException;

}
