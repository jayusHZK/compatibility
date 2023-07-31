package com.jayus.smallSpring.step17.context;

/**
 * @author : h zk
 * @date : 2023/7/31 16:03
 * @description :
 **/
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

}
