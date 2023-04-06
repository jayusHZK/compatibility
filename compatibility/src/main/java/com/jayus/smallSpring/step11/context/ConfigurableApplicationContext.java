package com.jayus.smallSpring.step11.context;

import com.jayus.smallSpring.step11.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/4/6 17:18
 * @description :
 **/
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
