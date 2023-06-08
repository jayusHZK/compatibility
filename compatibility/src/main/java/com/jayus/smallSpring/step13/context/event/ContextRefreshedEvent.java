package com.jayus.smallSpring.step13.context.event;

import com.jayus.smallSpring.step13.context.ApplicationEvent;

public class ContextRefreshedEvent extends ApplicationEvent {

    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
