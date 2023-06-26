package com.jayus.smallSpring.step14.context;

import com.jayus.smallSpring.step14.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/6/26 17:01
 * @description :
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
