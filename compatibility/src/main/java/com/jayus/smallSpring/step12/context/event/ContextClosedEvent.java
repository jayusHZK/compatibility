package com.jayus.smallSpring.step12.context.event;

/**
 * @author : h zk
 * @date : 2023/4/12 13:48
 * @description :
 **/
public class ContextClosedEvent extends ApplicationContextEvent{

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
