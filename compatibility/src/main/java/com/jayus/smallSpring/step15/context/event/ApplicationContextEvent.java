package com.jayus.smallSpring.step15.context.event;

import com.jayus.smallSpring.step15.context.ApplicationContext;
import com.jayus.smallSpring.step15.context.ApplicationEvent;

/**
 * @author : h zk
 * @date : 2023/7/10 17:22
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
