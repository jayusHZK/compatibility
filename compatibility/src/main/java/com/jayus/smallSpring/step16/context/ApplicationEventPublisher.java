package com.jayus.smallSpring.step16.context;

/**
 * @author : h zk
 * @date : 2023/7/17 15:12
 * @description :
 **/
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

}
