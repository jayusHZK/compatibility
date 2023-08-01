package com.jayus.smallSpring.step17.context.event;

import com.jayus.smallSpring.step17.context.ApplicationEvent;
import com.jayus.smallSpring.step17.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/8/1 10:31
 * @description :
 **/
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
