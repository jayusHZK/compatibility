package com.jayus.smallSpring.step09.beans.factory.config;

import com.jayus.smallSpring.step09.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/28 11:11
 * @description :
 **/
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean,String beanName)  throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException;

}
