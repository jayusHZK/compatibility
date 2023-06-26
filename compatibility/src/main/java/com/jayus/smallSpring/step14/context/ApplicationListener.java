package com.jayus.smallSpring.step14.context;

import java.util.EventListener;

/**
 * @author : h zk
 * @date : 2023/6/26 16:59
 * @description :
 **/
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);

}
