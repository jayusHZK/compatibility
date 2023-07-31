package com.jayus.smallSpring.step17.context;

import java.util.EventListener;

/**
 * @author : h zk
 * @date : 2023/7/31 16:03
 * @description :
 **/
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);

}
