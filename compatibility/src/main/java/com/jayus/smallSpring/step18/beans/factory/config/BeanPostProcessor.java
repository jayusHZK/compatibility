package com.jayus.smallSpring.step18.beans.factory.config;

import com.jayus.smallSpring.step18.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/8/3 18:10
 * @description :
 **/
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean,String beanName)  throws BeansException;

}
