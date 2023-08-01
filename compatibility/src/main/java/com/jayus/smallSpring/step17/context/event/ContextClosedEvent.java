package com.jayus.smallSpring.step17.context.event;

/**
 * @author : h zk
 * @date : 2023/8/1 10:30
 * @description :
 **/
public class ContextClosedEvent extends ApplicationContextEvent{

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
