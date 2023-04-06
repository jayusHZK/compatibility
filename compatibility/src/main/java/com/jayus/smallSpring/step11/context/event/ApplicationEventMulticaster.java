package com.jayus.smallSpring.step11.context.event;

import com.jayus.smallSpring.step11.context.ApplicationEvent;
import com.jayus.smallSpring.step11.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/4/6 17:36
 * @description :
 **/
public interface ApplicationEventMulticaster {

    void addApplicationListnener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
