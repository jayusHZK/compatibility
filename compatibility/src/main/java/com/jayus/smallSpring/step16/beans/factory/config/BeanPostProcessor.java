package com.jayus.smallSpring.step16.beans.factory.config;

import com.jayus.smallSpring.step16.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/7/14 11:47
 * @description :
 **/
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
