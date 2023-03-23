package com.jayus.smallSpring.step08.beans.factory.config;

import com.jayus.smallSpring.step08.beans.BeansException;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/23 23:19
 * @Version: 1.0
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean,String beanName)throws BeansException;

}
