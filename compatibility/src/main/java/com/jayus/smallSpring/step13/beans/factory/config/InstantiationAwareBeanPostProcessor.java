package com.jayus.smallSpring.step13.beans.factory.config;

import com.jayus.smallSpring.step13.beans.BeansException;
import com.jayus.smallSpring.step13.beans.PropertyValues;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     *  在 Bean 对象执行初始化方法之前，执行此方法
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass,String beanName) throws BeansException;

    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean,String beanName) throws BeansException;

}
