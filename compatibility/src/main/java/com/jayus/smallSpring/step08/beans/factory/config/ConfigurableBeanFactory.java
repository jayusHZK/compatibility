package com.jayus.smallSpring.step08.beans.factory.config;

import com.jayus.smallSpring.step08.beans.factory.HierarchicalBeanFactory;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/23 23:16
 * @Version: 1.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

}