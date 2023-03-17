package com.jayus.smallSpring.step07.beans.factory.config;

import com.jayus.smallSpring.step07.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/17 14:48
 * @description : 允许自定义修改 BeanDefinition 属性信息
 **/
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory() throws BeansException;

}
