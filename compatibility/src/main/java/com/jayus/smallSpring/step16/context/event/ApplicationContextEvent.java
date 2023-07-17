package com.jayus.smallSpring.step16.context.event;

import com.jayus.smallSpring.step16.context.ApplicationContext;
import com.jayus.smallSpring.step16.context.ApplicationEvent;

/**
 * @author : h zk
 * @date : 2023/7/17 15:21
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
