package com.jayus.smallSpring.step14.beans.factory.annotation;

import com.jayus.smallSpring.step14.beans.BeansException;
import com.jayus.smallSpring.step14.beans.PropertyValues;
import com.jayus.smallSpring.step14.beans.factory.BeanFactory;
import com.jayus.smallSpring.step14.beans.factory.BeanFactoryAware;
import com.jayus.smallSpring.step14.beans.factory.ConfigurableListableBeanFactory;
import com.jayus.smallSpring.step14.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * @author : h zk
 * @date : 2023/6/21 14:29
 * @description :
 **/
public class AutowiredAnnotationBeanProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }
}
