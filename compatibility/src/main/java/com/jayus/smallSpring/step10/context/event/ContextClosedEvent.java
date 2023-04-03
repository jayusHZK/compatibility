package com.jayus.smallSpring.step10.context.event;

/**
 * @author : h zk
 * @date : 2023/4/3 15:07
 * @description :
 **/
public class ContextClosedEvent extends ApplicationContextEvent{

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
