package com.jayus.smallSpring.step16.beans.factory.support;

import com.jayus.smallSpring.step16.beans.BeansException;
import com.jayus.smallSpring.step16.beans.factory.ObjectFactory;
import com.jayus.smallSpring.step16.beans.factory.config.AutowireCapableBeanFactory;
import com.jayus.smallSpring.step16.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step16.beans.factory.config.BeanPostProcessor;
import com.jayus.smallSpring.step16.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.lang.reflect.Constructor;

/**
 * @author : h zk
 * @date : 2023/7/14 17:24
 * @description :
 **/
public abstract class AbstractAutoCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = resolveBeforeInstantiation(beanName, beanDefinition);
        if (null != bean){
            return bean;
        }
        return doCraeteBean(beanName,beanDefinition,args);
    }

    protected Object doCraeteBean(String beanName,BeanDefinition beanDefinition,Object[] args){
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);

            // 处理循环依赖，将实例化后的 Bean 对象提前放入缓存中暴露出来
            if (beanDefinition.isSingleton()){
                Object finalBean = bean;
                addSingletonFactory(beanName,() -> getEarlyBeanReference(beanName,beanDefinition,finalBean));
            }
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
    }

    protected Object getEarlyBeanReference(String beanName,BeanDefinition beanDefinition,Object bean){
        Object exposedObject = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                exposedObject = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).getEarlyBeanReference(exposedObject, beanName);
                if (null != exposedObject) return exposedObject;
            }
        }
        return exposedObject;
    }

    protected Object resolveBeforeInstantiation(String beanName,BeanDefinition beanDefinition){
        Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (null != bean){
            bean = applyBeanPostProcessorsAfterInitialization(bean,beanName);
        }
        return bean;
    }

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class<?> beanClass,String beanName){
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
                if (result != null) return result;
            }
        }
        return null;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args){
        Constructor constructorToUse = null;
        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length){
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructorToUse,args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}
