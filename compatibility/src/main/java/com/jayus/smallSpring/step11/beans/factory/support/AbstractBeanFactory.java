package com.jayus.smallSpring.step11.beans.factory.support;

import com.jayus.smallSpring.step11.beans.BeansException;
import com.jayus.smallSpring.step11.beans.factory.FactoryBean;
import com.jayus.smallSpring.step11.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step11.beans.factory.config.BeanPostProcessor;
import com.jayus.smallSpring.step11.beans.factory.config.ConfigurableBeanFactory;
import com.jayus.smallSpring.step11.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2023/4/6 15:36
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

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            return (T) getObjectForBeanInstance(sharedInstance,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean,name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        Object object = getCachedObjectForFactoryBean(beanName);
        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            getObjectFromFactoryBean(factoryBean,beanName);
        }
        return object;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}
