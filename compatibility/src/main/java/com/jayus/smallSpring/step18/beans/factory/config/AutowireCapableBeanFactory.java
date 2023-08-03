package com.jayus.smallSpring.step18.beans.factory.config;

import com.jayus.smallSpring.step18.beans.BeansException;
import com.jayus.smallSpring.step18.beans.factory.BeanFactory;

/**
 * @author : h zk
 * @date : 2023/8/3 18:21
 * @description :
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName) throws BeansException;

}
