package com.jayus.smallSpring.step11.context;

/**
 * @author : h zk
 * @date : 2023/4/6 17:12
 * @description :
 **/
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

}
