package com.jayus.smallSpring.step15.context;

import com.jayus.smallSpring.step15.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/7/10 16:38
 * @description :
 **/
public interface ConfigurableApplicationContext extends ApplicationContext{

    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
