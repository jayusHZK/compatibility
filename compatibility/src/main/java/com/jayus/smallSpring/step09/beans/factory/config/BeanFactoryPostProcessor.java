package com.jayus.smallSpring.step09.beans.factory.config;

import com.jayus.smallSpring.step09.beans.BeansException;
import com.jayus.smallSpring.step09.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/3/28 11:23
 * @description :
 **/
public interface BeanFactoryPostProcessor {

    /**
     *  在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
