package com.jayus.smallSpring.step12.context;

import com.jayus.smallSpring.step12.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/4/12 11:39
 * @description :
 **/
public interface ConfigurableApplicationContext extends ApplicationContext{

    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
