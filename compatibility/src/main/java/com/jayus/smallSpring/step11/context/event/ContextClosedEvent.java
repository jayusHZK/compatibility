package com.jayus.smallSpring.step11.context.event;

/**
 * @author : h zk
 * @date : 2023/4/6 17:19
 * @description :
 **/
public class ContextClosedEvent extends ApplicationContextEvent{

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
