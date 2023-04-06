package com.jayus.smallSpring.step11.context.event;

import com.jayus.smallSpring.step11.beans.factory.BeanFactory;
import com.jayus.smallSpring.step11.context.ApplicationEvent;
import com.jayus.smallSpring.step11.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/4/6 18:24
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
