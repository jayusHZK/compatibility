package com.jayus.smallSpring.step11.beans.factory.config;

import com.jayus.smallSpring.step11.beans.BeansException;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/5 22:16
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
     * 在 Bean 对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException;

}
