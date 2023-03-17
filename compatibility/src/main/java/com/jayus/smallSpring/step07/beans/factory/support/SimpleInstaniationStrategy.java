package com.jayus.smallSpring.step07.beans.factory.support;

import com.jayus.smallSpring.step07.beans.BeansException;
import com.jayus.smallSpring.step07.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author : h zk
 * @date : 2023/3/17 15:19
 * @description :
 **/
public class SimpleInstaniationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (ctor != null){
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
