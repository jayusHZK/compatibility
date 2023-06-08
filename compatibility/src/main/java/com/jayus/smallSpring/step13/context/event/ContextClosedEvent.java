package com.jayus.smallSpring.step13.context.event;

import com.jayus.smallSpring.step13.context.ApplicationEvent;

public class ContextClosedEvent extends ApplicationEvent {

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
