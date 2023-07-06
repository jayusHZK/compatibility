package com.jayus.smallSpring.step15.beans.factory.config;

import com.jayus.smallSpring.step15.beans.BeansException;
import com.jayus.smallSpring.step15.beans.PropertyValue;
import com.jayus.smallSpring.step15.beans.PropertyValues;

/**
 * @author : h zk
 * @date : 2023/7/6 11:49
 * @description :
 **/
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化之前，执行此方法
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass,String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInstantiation(Object bean,String beanName)  throws BeansException;

    /**
     * 在 Bean 对象实例化完成后，设置属性操作之前执行此方法
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs,Object bean,String beanName) throws BeansException;

}
