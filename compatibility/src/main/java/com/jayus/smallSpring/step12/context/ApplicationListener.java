package com.jayus.smallSpring.step12.context;

import java.util.EventListener;

/**
 * @author : h zk
 * @date : 2023/4/12 11:24
 * @description :
 **/
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplication(E event);

}
