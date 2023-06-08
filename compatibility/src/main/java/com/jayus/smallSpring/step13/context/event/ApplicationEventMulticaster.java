package com.jayus.smallSpring.step13.context.event;

import com.jayus.smallSpring.step13.context.ApplicationEvent;
import com.jayus.smallSpring.step13.context.ApplicationListener;

public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicaseEvent(ApplicationEvent event);

}
