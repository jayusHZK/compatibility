package com.jayus.smallSpring.step14.context.event;

/**
 * @author : h zk
 * @date : 2023/6/26 17:38
 * @description :
 **/
public class ApplicationClosedEvent extends ApplicationContextEvent {

    public ApplicationClosedEvent(Object source) {
        super(source);
    }
}
