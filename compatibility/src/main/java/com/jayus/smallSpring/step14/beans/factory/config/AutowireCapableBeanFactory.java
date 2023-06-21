package com.jayus.smallSpring.step14.beans.factory.config;

import com.jayus.smallSpring.step14.beans.BeansException;
import com.jayus.smallSpring.step14.beans.factory.BeanFactory;

/**
 * @author : h zk
 * @date : 2023/6/21 14:50
 * @description :
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName) throws BeansException;

}
