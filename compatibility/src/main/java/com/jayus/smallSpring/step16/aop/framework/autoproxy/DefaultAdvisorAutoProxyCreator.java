package com.jayus.smallSpring.step16.aop.framework.autoproxy;

import com.jayus.smallSpring.step16.aop.Advisor;
import com.jayus.smallSpring.step16.aop.Pointcut;
import com.jayus.smallSpring.step16.beans.BeansException;
import com.jayus.smallSpring.step16.beans.factory.BeanFactory;
import com.jayus.smallSpring.step16.beans.factory.BeanFactoryAware;
import com.jayus.smallSpring.step16.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.jayus.smallSpring.step16.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : h zk
 * @date : 2023/7/17 18:09
 * @description :
 **/
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    private final Set<Object> earlyProxyReferences = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    private boolean isInfrastuctureClass(Class<?> beanClass){
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!earlyProxyReferences.contains(beanName)){
            return
        }
        return null;
    }

    protected Object wrapIfNecessary(Object bean,String beanName){
        if (isInfrastuctureClass(bean.getClass())) return bean;

    }
}
