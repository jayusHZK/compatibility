package com.jayus.smallSpring.step15.context.support;

import com.jayus.smallSpring.step15.beans.BeansException;
import com.jayus.smallSpring.step15.beans.factory.ConfigurableListableBeanFactory;
import com.jayus.smallSpring.step15.context.ConfigurableApplicationContext;
import com.jayus.smallSpring.step15.context.event.ApplicationEventMulticaster;
import com.jayus.smallSpring.step15.core.io.DefaultResourceLoader;

/**
 * @author : h zk
 * @date : 2023/7/10 18:18
 * @description :
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        invok
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

}
