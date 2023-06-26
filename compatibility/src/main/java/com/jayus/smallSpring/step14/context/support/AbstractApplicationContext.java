package com.jayus.smallSpring.step14.context.support;

import com.jayus.smallSpring.step14.beans.BeansException;
import com.jayus.smallSpring.step14.beans.factory.ConfigurableListableBeanFactory;
import com.jayus.smallSpring.step14.context.ConfigurableApplicationContext;
import com.jayus.smallSpring.step14.context.event.ApplicationEventMulticaster;
import com.jayus.smallSpring.step14.core.io.DefaultResourceLoader;

/**
 * @author : h zk
 * @date : 2023/6/26 18:22
 * @description :
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {

    }
}
