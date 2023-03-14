package com.jayus.smallSpring.step06.beans.factory.config;

import com.jayus.smallSpring.step06.beans.factory.HierarchicalBeanFactory;

/**
 * @author : h zk
 * @date : 2023/3/14 17:45
 * @description :
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistrt {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
