package com.jayus.smallSpring.step10.context.event;

import com.jayus.smallSpring.step10.context.ApplicationEvent;
import com.jayus.smallSpring.step10.context.ApplicationListener;

/**
 * @author : h zk
 * @date : 2023/4/3 15:10
 * @description :
 **/
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener listener);

    void removeApplicationListener(ApplicationListener listener);

    void multicastEvent(ApplicationEvent event);

}
