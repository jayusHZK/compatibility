package com.jayus.smallSpring.step16.beans.factory.config;

import com.jayus.smallSpring.step16.beans.BeansException;
import com.jayus.smallSpring.step16.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/7/14 14:29
 * @description : 允许自定义修改 BeanDefinition 属性信息
 **/
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
