package com.jayus.smallSpring.step17.beans.factory.config;

import com.jayus.smallSpring.step17.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/7/27 11:31
 * @description :
 **/
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException;

}
