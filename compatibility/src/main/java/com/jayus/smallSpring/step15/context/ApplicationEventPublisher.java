package com.jayus.smallSpring.step15.context;

/**
 * @author : h zk
 * @date : 2023/7/10 16:32
 * @description :
 **/
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

}
