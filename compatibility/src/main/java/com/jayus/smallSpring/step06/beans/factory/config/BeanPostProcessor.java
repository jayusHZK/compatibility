package com.jayus.smallSpring.step06.beans.factory.config;

import com.jayus.smallSpring.step06.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/14 17:40
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
