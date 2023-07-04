package com.jayus.smallSpring.step10.context.event;

import com.jayus.smallSpring.step10.context.ApplicationEvent;

/**
 * @author : h zk
 * @date : 2023/4/3 15:07
 * @description :
 **/
public class ContextClosedEvent extends ApplicationEvent {

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
