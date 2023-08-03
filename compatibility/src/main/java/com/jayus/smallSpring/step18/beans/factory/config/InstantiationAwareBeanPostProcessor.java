package com.jayus.smallSpring.step18.beans.factory.config;

import com.jayus.smallSpring.step18.beans.BeansException;
import com.jayus.smallSpring.step18.beans.PropertyValues;

/**
 * @author : h zk
 * @date : 2023/8/3 18:23
 * @description :
 **/
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    Object postProcessBeforeInstantiation(Class<?> beanClass,String beanName) throws BeansException;

    boolean postProcessAfterInstantiation(Object bean,String beanName) throws BeansException;

    PropertyValues postProcessPropertyValues(PropertyValues pvs,Object bean,String beanName) throws BeansException;

    default Object getEarlyBeanReference(Object bean,String beanName) {
        return bean;
    }

}
