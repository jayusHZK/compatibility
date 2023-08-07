package com.jayus.smallSpring.step18.context.event;

import com.jayus.smallSpring.step18.context.ApplicationEvent;
import com.jayus.smallSpring.step18.context.ApplicationListener;

public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
