package com.jayus.smallSpring.step04.factory.support;

import com.jayus.smallSpring.step03.exception.BeanException;
import com.jayus.smallSpring.step03.factory.BeanFactory;
import com.jayus.smallSpring.step04.BeansException;
import com.jayus.smallSpring.step04.factory.config.BeanDefinition;

/**
 * @Author: h zk
 * @Description: 抽象bean工厂
 * @Date: 2023/3/12 14:19
 * @Version: 1.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeanException {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeanException {
        return doGetBean(name,args);
    }

    protected <T> T doGetBean(final String name,final Object[] args){
        Object bean = getSingleton(name);
        if (bean != null){
            return (T)bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T)createBean(name,beanDefinition,args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws BeansException;
}