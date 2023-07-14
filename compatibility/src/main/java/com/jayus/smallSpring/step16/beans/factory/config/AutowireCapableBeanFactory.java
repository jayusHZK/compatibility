package com.jayus.smallSpring.step16.beans.factory.config;

import com.jayus.smallSpring.step16.beans.BeansException;
import com.jayus.smallSpring.step16.beans.factory.BeanFactory;
import com.jayus.smallSpring.step16.beans.factory.ObjectFactory;

/**
 * @author : h zk
 * @date : 2023/7/14 17:25
 * @description :
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName) throws BeansException;

}
