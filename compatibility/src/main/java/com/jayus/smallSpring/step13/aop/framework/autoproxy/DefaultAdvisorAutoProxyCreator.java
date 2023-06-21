package com.jayus.smallSpring.step13.aop.framework.autoproxy;

import com.jayus.smallSpring.step13.aop.AdvisedSupport;
import com.jayus.smallSpring.step13.aop.Advisor;
import com.jayus.smallSpring.step13.aop.ClassFilter;
import com.jayus.smallSpring.step13.aop.TargetSource;
import com.jayus.smallSpring.step13.aop.aspectj.AspectJexpressionPointcutAdvisor;
import com.jayus.smallSpring.step13.aop.framework.ProxyFactory;
import com.jayus.smallSpring.step13.beans.BeansException;
import com.jayus.smallSpring.step13.beans.PropertyValues;
import com.jayus.smallSpring.step13.beans.factory.BeanFactory;
import com.jayus.smallSpring.step13.beans.factory.BeanFactoryAware;
import com.jayus.smallSpring.step13.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.jayus.smallSpring.step13.beans.factory.support.DefaultListableBeanFactory;
import com.jayus.smallSpring.step14.aop.Pointcut;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * @author : h zk
 * @date : 2023/6/21 10:34
 * @description :
 **/
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (isInfrastructureClass(beanClass)) return null;
        Collection<AspectJexpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJexpressionPointcutAdvisor.class).values();
        for (AspectJexpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) continue;
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass){
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }
}
