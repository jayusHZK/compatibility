package com.jayus.smallSpring.step14.beans.factory.config;

import com.jayus.smallSpring.step14.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/6/21 14:39
 * @description :
 **/
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException;

}
