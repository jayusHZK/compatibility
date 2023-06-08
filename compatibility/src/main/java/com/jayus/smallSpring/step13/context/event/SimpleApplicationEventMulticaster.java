package com.jayus.smallSpring.step13.context.event;

import com.jayus.smallSpring.step13.beans.factory.BeanFactory;
import com.jayus.smallSpring.step13.context.ApplicationEvent;
import com.jayus.smallSpring.step13.context.ApplicationListener;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicaseEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
