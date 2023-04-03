package com.jayus.smallSpring.step10.context;

/**
 * @author : h zk
 * @date : 2023/4/3 14:58
 * @description :
 **/
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

}
