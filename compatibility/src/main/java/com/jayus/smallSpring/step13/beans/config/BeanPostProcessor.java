package com.jayus.smallSpring.step13.beans.config;

import com.jayus.smallSpring.step13.beans.BeansException;

public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException;

}
