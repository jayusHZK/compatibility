package com.jayus.smallSpring.step15.context.event;

import com.jayus.smallSpring.step15.context.ApplicationEvent;

/**
 * @author : h zk
 * @date : 2023/7/10 17:24
 * @description :
 **/
public class ContextClosedEvent extends ApplicationEvent {

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
