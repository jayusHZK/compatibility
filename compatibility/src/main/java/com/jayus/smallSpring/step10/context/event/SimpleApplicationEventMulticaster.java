package com.jayus.smallSpring.step10.context.event;

import com.jayus.smallSpring.step10.beans.factory.BeanFactory;
import com.jayus.smallSpring.step10.context.ApplicationEvent;
import com.jayus.smallSpring.step10.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/4/3 15:14
 * @description :
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
