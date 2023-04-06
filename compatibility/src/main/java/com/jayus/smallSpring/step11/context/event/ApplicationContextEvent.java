package com.jayus.smallSpring.step11.context.event;

import com.jayus.smallSpring.step11.context.ApplicationEvent;

/**
 * @author : h zk
 * @date : 2023/4/6 17:09
 * @description :
 **/
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContextEvent getApplicationContext(){
        return (ApplicationContextEvent) getSource();
    }

}
