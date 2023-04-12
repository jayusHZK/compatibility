package com.jayus.smallSpring.step12.context.event;

import com.jayus.smallSpring.step12.context.ApplicationEvent;

/**
 * @author : h zk
 * @date : 2023/4/12 13:49
 * @description :
 **/
public class ContextRefreshedEvent extends ApplicationEvent {

    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
