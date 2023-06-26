package com.jayus.smallSpring.step14.context;

/**
 * @author : h zk
 * @date : 2023/6/26 16:56
 * @description :
 **/
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

}
