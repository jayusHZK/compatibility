package com.jayus.smallSpring.step11.context.event;

/**
 * @author : h zk
 * @date : 2023/4/6 17:20
 * @description :
 **/
public class ContextRefreshedEvent extends ApplicationContextEvent {

    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
