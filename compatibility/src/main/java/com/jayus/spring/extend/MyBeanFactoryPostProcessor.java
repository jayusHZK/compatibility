package com.jayus.spring.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor, BeanPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //configurableListableBeanFactory.
        System.out.println("MyBeanFactoryPostProcessor:postProcessBeanFactory");

    }
}
