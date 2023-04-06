package com.jayus.smallSpring.step11.context.support;

import com.jayus.smallSpring.step11.beans.BeansException;
import com.jayus.smallSpring.step11.beans.factory.config.BeanPostProcessor;
import com.jayus.smallSpring.step11.context.ApplicationContext;
import com.jayus.smallSpring.step11.context.ApplicationContextAware;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/6 22:41
 * @Version: 1.0
 */
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