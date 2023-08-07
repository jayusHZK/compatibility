package com.jayus.smallSpring.step18.context.event;

import com.jayus.smallSpring.step18.beans.factory.BeanFactory;
import com.jayus.smallSpring.step18.context.ApplicationEvent;
import com.jayus.smallSpring.step18.context.ApplicationListener;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
