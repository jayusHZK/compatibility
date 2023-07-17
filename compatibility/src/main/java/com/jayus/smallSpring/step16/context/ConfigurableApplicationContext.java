package com.jayus.smallSpring.step16.context;

import com.jayus.smallSpring.step16.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/7/17 15:18
 * @description :
 **/
public interface ConfigurableApplicationContext extends ApplicationContext{

    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
