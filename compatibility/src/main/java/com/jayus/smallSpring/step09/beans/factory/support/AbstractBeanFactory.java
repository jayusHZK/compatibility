package com.jayus.smallSpring.step09.beans.factory.support;

import com.jayus.smallSpring.step09.beans.BeansException;
import com.jayus.smallSpring.step09.beans.factory.FactoryBean;
import com.jayus.smallSpring.step09.beans.factory.config.BeanPostProcessor;
import com.jayus.smallSpring.step09.beans.factory.config.ConfigurableBeanFactory;
import com.jayus.smallSpring.step09.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2023/3/28 19:52
 * @description :
 **/
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        return null;
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return null;
    }

    private Object doGetBean(final String name, final Object[] args) {
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            return geto
        }
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        getCacheObjectForFactoryBean()
    }
}
