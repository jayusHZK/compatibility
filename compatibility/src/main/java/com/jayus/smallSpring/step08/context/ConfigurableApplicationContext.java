package com.jayus.smallSpring.step08.context;

import com.jayus.smallSpring.step08.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/27 15:01
 * @description :
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
