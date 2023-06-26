package com.jayus.smallSpring.step14.context.event;

import com.jayus.smallSpring.step14.context.ApplicationEvent;
import com.jayus.smallSpring.step14.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/6/26 17:33
 * @description :
 **/
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
