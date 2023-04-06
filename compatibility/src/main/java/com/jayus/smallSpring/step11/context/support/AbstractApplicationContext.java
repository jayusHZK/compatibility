package com.jayus.smallSpring.step11.context.support;

import com.jayus.smallSpring.step11.beans.BeansException;
import com.jayus.smallSpring.step11.context.ConfigurableApplicationContext;
import com.jayus.smallSpring.step11.context.event.ApplicationEventMulticaster;
import com.jayus.smallSpring.step11.core.io.DefaultResourceLoader;

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

    }
}
