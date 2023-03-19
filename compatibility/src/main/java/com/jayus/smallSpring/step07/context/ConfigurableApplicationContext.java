package com.jayus.smallSpring.step07.context;

import com.jayus.smallSpring.step07.beans.BeansException;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/19 22:29
 * @Version: 1.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registShutdownHook();

    void close();

}
