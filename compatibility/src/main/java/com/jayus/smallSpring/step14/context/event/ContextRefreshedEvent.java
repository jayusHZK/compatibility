package com.jayus.smallSpring.step14.context.event;

/**
 * @author : h zk
 * @date : 2023/6/26 17:39
 * @description :
 **/
public class ContextRefreshedEvent extends ApplicationContextEvent {

    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
