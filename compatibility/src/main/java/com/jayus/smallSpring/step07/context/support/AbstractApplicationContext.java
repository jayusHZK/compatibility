package com.jayus.smallSpring.step07.context.support;

import com.jayus.smallSpring.step07.beans.BeansException;
import com.jayus.smallSpring.step07.beans.factory.ConfigurableListableBeanFactory;
import com.jayus.smallSpring.step07.beans.factory.config.BeanFactoryPostProcessor;
import com.jayus.smallSpring.step07.beans.factory.config.BeanPostPorcessor;
import com.jayus.smallSpring.step07.context.ConfigurableApplicationContext;
import com.jayus.smallSpring.step07.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/19 22:27
 * @Version: 1.0
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        // 创建 BeanFactory ，并加在 BeanDefinition
        refreshBeanFactory();

        // 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);

        // BeanPostProcessor 需要提前其他 Bean 对象实例化之前执行注册操作
        registerBeanProcessors(beanFactory);

        // 提前实例化单例 Bean 对象
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostPorcessor> beanPostPorcessorMap = beanFactory.getBeansOfType(BeanPostPorcessor.class);
        for (BeanPostPorcessor beanPostPorcessor : beanPostPorcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostPorcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
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
        return getBeanFactory().getBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requireType) throws BeansException {
        return getBeanFactory().getBean(name,requireType);
    }

    @Override
    public void registShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destorySingletons();
    }
}