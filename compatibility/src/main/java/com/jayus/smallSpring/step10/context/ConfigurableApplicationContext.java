package com.jayus.smallSpring.step10.context;

import com.jayus.smallSpring.step10.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/4/3 14:59
 * @description :
 **/
public interface ConfigurableApplicationContext extends ApplicationContext{

    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
