package com.jayus.smallSpring.step16.context.event;

/**
 * @author : h zk
 * @date : 2023/7/17 15:22
 * @description :
 **/
public class ContextClosedEvent extends ApplicationContextEvent{

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
