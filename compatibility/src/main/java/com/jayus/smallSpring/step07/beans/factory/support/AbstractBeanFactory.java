package com.jayus.smallSpring.step07.beans.factory.support;

import com.jayus.smallSpring.step07.beans.BeansException;
import com.jayus.smallSpring.step07.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step07.beans.factory.config.BeanPostPorcessor;
import com.jayus.smallSpring.step07.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2023/3/17 16:52
 * @description :
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostPorcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requireType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name,final Object[] args){
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name,beanDefinition,args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostPorcessor beanPostPorcessor) {
        this.beanPostProcessors.remove(beanPostPorcessor);
        this.beanPostProcessors.add(beanPostPorcessor);
    }

    public List<BeanPostPorcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }
}
