package com.jayus.smallSpring.step15.context.event;

/**
 * @author : h zk
 * @date : 2023/7/10 17:24
 * @description :
 **/
public class ContextRefreshEvent extends ApplicationContextEvent{

    public ContextRefreshEvent(Object source) {
        super(source);
    }
}
