package com.jayus.smallSpring.step12.beans.factory.config;

import com.jayus.smallSpring.step12.beans.BeansException;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 19:20
 * @Version: 1.0
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化之前，执行此方法
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass,String beanName) throws BeansException;

}
