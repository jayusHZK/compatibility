package com.jayus.smallSpring.step14.beans.factory.support;

import com.jayus.smallSpring.step14.beans.factory.config.AutowireCapableBeanFactory;
import com.jayus.smallSpring.step14.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step14.beans.factory.config.BeanPostProcessor;
import com.jayus.smallSpring.step14.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * @author : h zk
 * @date : 2023/6/21 16:21
 * @description :
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
        implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();


    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (null != bean) {
            bean = a
        }
    }

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class<?> beanClass, String beanName) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = beanPostProcessor.postProcessBeforeInitialization(beanClass, beanName);
                if (null != result) return result;
            }
        }
        return null;
    }

}
