package com.jayus.smallSpring.step14.context.event;

import com.jayus.smallSpring.step14.context.ApplicationContext;
import com.jayus.smallSpring.step14.context.ApplicationEvent;

/**
 * @author : h zk
 * @date : 2023/6/26 17:32
 * @description :
 **/
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
