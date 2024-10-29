package com.jayus.spring.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

/*
BeanFactoryPostProcessor 在 bean 未实例化前 操作 bean 定义，只能修改
BeanPostProcessor 在bean 未注入前 操作 bean 注入
 */
@Configuration
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor, BeanPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        /*BeanDefinition a = configurableListableBeanFactory.getBeanDefinition("a");
        MutablePropertyValues propertyValues = a.getPropertyValues();
        // 修改属性
        propertyValues.addPropertyValue("name","min");
        propertyValues.addPropertyValue("sex","nv");
        propertyValues.addPropertyValue("name", new TestCondition());*/
        //configurableListableBeanFactory.
        //System.out.println("MyBeanFactoryPostProcessor:postProcessBeanFactory");
    }



}
