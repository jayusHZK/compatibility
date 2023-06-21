package com.jayus.smallSpring.step14.beans.factory.config;

import com.jayus.smallSpring.step14.beans.BeansException;
import com.jayus.smallSpring.step14.beans.PropertyValues;

/**
 * @author : h zk
 * @date : 2023/6/21 15:00
 * @description :
 **/
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    PropertyValues postProcessPropertyValues(PropertyValues pvs,Object bean,String beanName) throws BeansException;

}
