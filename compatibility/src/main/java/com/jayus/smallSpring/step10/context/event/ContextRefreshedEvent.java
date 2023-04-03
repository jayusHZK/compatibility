package com.jayus.smallSpring.step10.context.event;

/**
 * @author : h zk
 * @date : 2023/4/3 15:08
 * @description :
 **/
public class ContextRefreshedEvent extends ApplicationContextEvent{

    public ContextRefreshedEvent(Object source) {
        super(source);
    }

}
