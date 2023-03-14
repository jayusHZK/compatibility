package com.jayus.smallSpring.step04.factory.support;

import com.jayus.smallSpring.step04.BeansException;
import com.jayus.smallSpring.step04.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/12 16:29
 * @Version: 1.0
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();


    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }
}