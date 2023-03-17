package com.jayus.smallSpring.step07.beans.factory.config;

import com.jayus.smallSpring.step07.beans.factory.HierarchicalBeanFactory;

/**
 * @author : h zk
 * @date : 2023/3/17 15:06
 * @description :
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostPorcessor beanPostPorcessor);

}
