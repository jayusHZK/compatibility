package com.jayus.smallSpring.step17.context.event;

import com.jayus.smallSpring.step17.beans.factory.BeanFactory;
import com.jayus.smallSpring.step17.context.ApplicationEvent;
import com.jayus.smallSpring.step17.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/8/1 10:45
 * @description :
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(final ApplicationEvent event){
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }

}
