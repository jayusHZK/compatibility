package com.jayus.smallSpring.step12.context.event;


import com.jayus.smallSpring.step12.context.ApplicationContext;
import com.jayus.smallSpring.step12.context.ApplicationEvent;

/**
 * @author : h zk
 * @date : 2023/4/12 11:46
 * @description :
 **/
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
