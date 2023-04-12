package com.jayus.smallSpring.step12.context;

/**
 * @author : h zk
 * @date : 2023/4/12 11:42
 * @description :
 **/
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

}
