package com.jayus.smallSpring.step10.event;

import com.jayus.smallSpring.step10.context.ApplicationListener;
import com.jayus.smallSpring.step10.context.event.ContextClosedEvent;

/**
 * @author : h zk
 * @date : 2023/4/3 18:05
 * @description :
 **/
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }

}
