package com.jayus.smallSpring.step03.factory.support;

import com.jayus.smallSpring.step03.exception.BeanException;
import com.jayus.smallSpring.step03.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: h zk
 * @Description: 默认类工厂
 * @Date: 2023/3/10 23:40
 * @Version: 1.0
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name,beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeanException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeanException("No bean named '"+beanName + "is defined");
        return beanDefinition;
    }
}