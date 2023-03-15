package com.jayus.smallSpring.step06.context;

import com.jayus.smallSpring.step06.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/15 9:44
 * @description :
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

}
