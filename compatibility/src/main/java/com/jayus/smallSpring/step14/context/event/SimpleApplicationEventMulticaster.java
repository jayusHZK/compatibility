package com.jayus.smallSpring.step14.context.event;

import com.jayus.smallSpring.step14.beans.factory.BeanFactory;
import com.jayus.smallSpring.step14.context.ApplicationEvent;
import com.jayus.smallSpring.step14.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/6/26 18:01
 * @description :
 **/
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
