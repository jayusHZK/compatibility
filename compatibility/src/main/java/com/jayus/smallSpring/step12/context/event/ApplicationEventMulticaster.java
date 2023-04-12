package com.jayus.smallSpring.step12.context.event;

import com.jayus.smallSpring.step12.context.ApplicationEvent;
import com.jayus.smallSpring.step12.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/4/12 14:11
 * @description :
 **/
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
