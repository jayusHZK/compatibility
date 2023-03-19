package com.jayus.smallSpring.step07.beans.factory.config;

import com.jayus.smallSpring.step07.beans.BeansException;
import com.jayus.smallSpring.step07.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/3/17 14:48
 * @description : 允许自定义修改 BeanDefinition 属性信息
 **/
public interface BeanFactoryPostProcessor {

    /**
     *  在所有 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
