package com.jayus.smallSpring.step10.context;

import java.util.EventListener;

/**
 * @author : h zk
 * @date : 2023/4/3 14:10
 * @description :
 **/
public interface ApplicationListener <E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);

}
