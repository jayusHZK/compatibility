package com.jayus.smallSpring.step10.context.event;

import com.jayus.smallSpring.step10.context.ApplicationContext;
import com.jayus.smallSpring.step10.context.ApplicationEvent;

/**
 * @author : h zk
 * @date : 2023/4/3 14:06
 * @description : 事件顶级父类
 **/
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
