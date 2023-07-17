package com.jayus.smallSpring.step16.context.event;

import com.jayus.smallSpring.step16.beans.factory.BeanFactory;
import com.jayus.smallSpring.step16.context.ApplicationEvent;
import com.jayus.smallSpring.step16.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/7/17 15:50
 * @description :
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

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
