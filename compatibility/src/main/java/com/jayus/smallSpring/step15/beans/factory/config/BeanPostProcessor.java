package com.jayus.smallSpring.step15.beans.factory.config;

import com.jayus.smallSpring.step15.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/7/6 11:38
 * @description :
 **/
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException;

}
