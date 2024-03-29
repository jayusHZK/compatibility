package com.jayus.smallSpring.step17.aop.framework.autoproxy;

import com.jayus.smallSpring.step17.aop.*;
import com.jayus.smallSpring.step17.aop.aspectj.AspectJExprossionPointcutAdvisor;
import com.jayus.smallSpring.step17.aop.framework.ProxyFactory;
import com.jayus.smallSpring.step17.beans.BeansException;
import com.jayus.smallSpring.step17.beans.PropertyValues;
import com.jayus.smallSpring.step17.beans.factory.BeanFactory;
import com.jayus.smallSpring.step17.beans.factory.BeanFactoryAware;
import com.jayus.smallSpring.step17.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.jayus.smallSpring.step17.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : h zk
 * @date : 2023/8/1 15:58
 * @description :
 **/
public class DefaultAdvisorAutoProxtCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

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

    private boolean isInfrastructureClass(Class<?> beanClass){
        return Advice.class.isAssignableFrom(beanClass)
                || Pointcut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!earlyProxyReferences.contains(beanName)){
            return wrapIfNecessary(bean,beanName);
        }
        return bean;
    }

    protected Object wrapIfNecessary(Object bean,String beanName){
        if (isInfrastructureClass(bean.getClass())) return bean;
        Collection<AspectJExprossionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExprossionPointcutAdvisor.class).values();
        for (AspectJExprossionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(bean.getClass())) continue;
            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(bean);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodMatcher((MethodMatcher) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(true);
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return bean;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) {
        earlyProxyReferences.add(beanName);
        return wrapIfNecessary(bean,beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }
}
