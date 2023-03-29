package com.jayus.smallSpring.step09.context;

import com.jayus.smallSpring.step09.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/29 14:31
 * @description :
 **/
public interface ConfigurableApplicationContext extends ApplicationContext{

    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
