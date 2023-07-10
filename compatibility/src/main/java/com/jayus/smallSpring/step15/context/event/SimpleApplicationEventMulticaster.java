package com.jayus.smallSpring.step15.context.event;

import com.jayus.smallSpring.step15.beans.factory.BeanFactory;
import com.jayus.smallSpring.step15.context.ApplicationEvent;
import com.jayus.smallSpring.step15.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/7/10 18:15
 * @description :
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFacotory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
