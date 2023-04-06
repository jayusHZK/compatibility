package com.jayus.smallSpring.step11.context.support;

import com.jayus.smallSpring.step11.beans.BeansException;
import com.jayus.smallSpring.step11.beans.factory.ConfigurableListenableBeanFactory;
import com.jayus.smallSpring.step11.beans.factory.config.BeanFactoryPostProcessor;
import com.jayus.smallSpring.step11.context.ApplicationEvent;
import com.jayus.smallSpring.step11.context.ApplicationListener;
import com.jayus.smallSpring.step11.context.ConfigurableApplicationContext;
import com.jayus.smallSpring.step11.context.event.ApplicationEventMulticaster;
import com.jayus.smallSpring.step11.context.event.ContextRefreshedEvent;
import com.jayus.smallSpring.step11.context.event.SimpleApplicationEventMulticaster;
import com.jayus.smallSpring.step11.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @author : h zk
 * @date : 2023/4/6 18:28
 * @description :
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        // 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();
        // 获取 BeanFactory
        ConfigurableListenableBeanFactory beanFactory = getBeanFactory();
        // 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        // BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        invokeBeanFactoryPostProcessors(beanFactory);
        // 初始化事件发布者
        initApplicationEventMulticaster();
        // 注册事件监听器
        registerListeners();
        // 提前实例化单例 Bean 对象
        beanFactory.preInstantiateSingletons();
        // 发布容器刷新完成事件
        finishRefresh();

    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListenableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListenableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postPostProcessBeanFactory(beanFactory);
        }
    }

    private void initApplicationEventMulticaster(){
        ConfigurableListenableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,applicationEventMulticaster);
    }

    private void registerListeners(){
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListnener(listener);
        }
    }

    private void finishRefresh(){
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
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
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name,requiredType);
    }

    @Override
    public void registerShutdownHook() {
        //Runnable runnable = this::close;
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        publishEvent(new ContextRefreshedEvent(this));
        getBeanFactory().destroySingletons();
    }
}
