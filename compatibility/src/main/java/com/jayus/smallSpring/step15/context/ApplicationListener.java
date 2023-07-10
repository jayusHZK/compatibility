package com.jayus.smallSpring.step15.context;

import java.util.EventListener;

/**
 * @author : h zk
 * @date : 2023/7/10 16:32
 * @description :
 **/
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);

}
