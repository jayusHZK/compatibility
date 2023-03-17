package com.jayus.smallSpring.step07.beans.factory.config;

import com.jayus.smallSpring.step07.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/17 14:43
 * @description :
 **/
public interface BeanPostPorcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException;

}
