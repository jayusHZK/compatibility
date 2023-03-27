package com.jayus.smallSpring.step08.context.support;

import com.jayus.smallSpring.step08.beans.BeansException;
import com.jayus.smallSpring.step08.beans.factory.config.BeanPostProcessor;
import com.jayus.smallSpring.step08.context.ApplicationContext;
import com.jayus.smallSpring.step08.context.ApplicationContextAware;

/**
 * @author : h zk
 * @date : 2023/3/27 15:58
 * @description :
 **/
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
