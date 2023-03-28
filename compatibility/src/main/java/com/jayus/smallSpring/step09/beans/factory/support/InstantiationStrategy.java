package com.jayus.smallSpring.step09.beans.factory.support;

import com.jayus.smallSpring.step09.beans.BeansException;
import com.jayus.smallSpring.step09.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author : h zk
 * @date : 2023/3/28 14:31
 * @description :
 **/
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args) throws BeansException;

}
