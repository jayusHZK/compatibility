package com.jayus.smallSpring.step16.beans.factory.config;

import com.jayus.smallSpring.step16.beans.BeansException;
import com.jayus.smallSpring.step16.beans.PropertyValues;

/**
 * @author : h zk
 * @date : 2023/7/14 11:49
 * @description :
 **/
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object postProcessBeforeInstantiation(Class<?> beanClass,String beanName) throws BeansException;

    boolean postProcessAfterInstantiation(Object bean,String beanName) throws BeansException;

    PropertyValues postProcessPropertyValues(PropertyValues pvs,Object bean,String beanName) throws BeansException;

    default Object getEarlyBeanReference(Object bean,String beanName){
        return bean;
    }

}
