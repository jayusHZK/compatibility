package com.jayus.spring.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/*
获取所有 bean 定义值，只能添加
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        /*for (String beanDefinitionName : beanDefinitionRegistry.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        // 添加 BeanDefinition
        beanDefinitionRegistry.registerBeanDefinition("a",new RootBeanDefinition(TestCondition.class));*/
        //System.out.println("MyBeanDefinitionRegistryPostProcessor:postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //System.out.println("MyBeanDefinitionRegistryPostProcessor:postProcessBeanFactory");
    }

}
