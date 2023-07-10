package com.jayus.smallSpring.step15.context.event;

import com.jayus.smallSpring.step15.context.ApplicationEvent;
import com.jayus.smallSpring.step15.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/7/10 17:25
 * @description :
 **/
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
