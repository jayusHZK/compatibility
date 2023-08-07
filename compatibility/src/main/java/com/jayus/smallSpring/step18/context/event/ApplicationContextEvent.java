package com.jayus.smallSpring.step18.context.event;

import com.jayus.smallSpring.step18.context.ApplicationContext;
import com.jayus.smallSpring.step18.context.ApplicationEvent;

public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
