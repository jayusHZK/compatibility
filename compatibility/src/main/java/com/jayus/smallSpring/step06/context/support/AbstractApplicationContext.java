package com.jayus.smallSpring.step06.context.support;

import com.jayus.smallSpring.step06.beans.BeansException;
import com.jayus.smallSpring.step06.beans.factory.ConfigurableListableBeanFactory;
import com.jayus.smallSpring.step06.beans.factory.config.BeanFactoryPostProcessor;
import com.jayus.smallSpring.step06.beans.factory.config.BeanPostProcessor;
import com.jayus.smallSpring.step06.context.ConfigurableApplicationContext;
import com.jayus.smallSpring.step06.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/3/15 9:45
 * @description :
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        // 创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();
        // 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 在 Bean 实例化之前 执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        // BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);
        // 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeanOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeanOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeanOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }
}
