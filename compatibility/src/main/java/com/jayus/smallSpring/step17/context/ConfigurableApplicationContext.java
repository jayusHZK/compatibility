package com.jayus.smallSpring.step17.context;

import com.jayus.smallSpring.step17.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/8/1 10:07
 * @description :
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
