package com.jayus.smallSpring.step11.context;

import java.util.EventListener;

/**
 * @author : h zk
 * @date : 2023/4/6 17:10
 * @description :
 **/
public interface ApplicationListener <E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);

}
