package com.jayus.smallSpring.step02.factory.support;

import com.jayus.smallSpring.step02.exception.BeanException;
import com.jayus.smallSpring.step02.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/8 23:15
 * @Version: 1.0
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
        implements BeanDefinitionRegistry {

    private final Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getbeanDefinition(String beanName) throws BeanException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeanException("No Bean named '"+beanName +"' is defined");
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }



}