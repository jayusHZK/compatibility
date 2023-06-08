package com.jayus.smallSpring.step13.context.support;

import com.jayus.smallSpring.step13.context.ConfigurableApplicationContext;
import com.jayus.smallSpring.step13.context.event.ApplicationEventMulticaster;
import com.jayus.smallSpring.step13.core.io.DefaultResourceLoader;

public abstract class AbstractApplicationContext extends DefaultResourceLoader
        implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;




}
