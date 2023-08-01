package com.jayus.smallSpring.step17.context.event;

import com.jayus.smallSpring.step17.context.ApplicationContext;
import com.jayus.smallSpring.step17.context.ApplicationEvent;

/**
 * @author : h zk
 * @date : 2023/8/1 10:28
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
