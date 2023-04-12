package com.jayus.smallSpring.step12.context.event;

import com.jayus.smallSpring.step12.beans.factory.BeanFactory;
import com.jayus.smallSpring.step12.context.ApplicationEvent;
import com.jayus.smallSpring.step12.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/4/12 14:53
 * @description :
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplication(event);
        }
    }
}
