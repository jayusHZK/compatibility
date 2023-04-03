package com.jayus.smallSpring.step10.event;

import com.jayus.smallSpring.step10.context.ApplicationListener;
import com.jayus.smallSpring.step10.context.event.ContextRefreshedEvent;

/**
 * @author : h zk
 * @date : 2023/4/3 18:07
 * @description :
 **/
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" +  this.getClass().getName());
    }

}
