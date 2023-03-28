package com.jayus.smallSpring.step09.beans.factory.config;

import com.jayus.smallSpring.step09.beans.factory.HierarchicalBeanFactory;

/**
 * @author : h zk
 * @date : 2023/3/28 11:10
 * @description :
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destorySingletons();

}
