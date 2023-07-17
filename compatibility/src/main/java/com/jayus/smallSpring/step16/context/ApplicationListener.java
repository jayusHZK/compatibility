package com.jayus.smallSpring.step16.context;

import java.util.EventListener;

/**
 * @author : h zk
 * @date : 2023/7/17 15:14
 * @description :
 **/
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);

}
