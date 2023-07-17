package com.jayus.smallSpring.step16.context.event;

import com.jayus.smallSpring.step16.context.ApplicationEvent;
import com.jayus.smallSpring.step16.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/7/17 15:23
 * @description :
 **/
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
