package com.jayus.smallSpring.step10.event;

import com.jayus.smallSpring.step10.context.ApplicationListener;

import java.util.Date;

/**
 * @author : h zk
 * @date : 2023/4/3 17:57
 * @description :
 **/
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到："+ event.getSource() + "消息；时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }

}
