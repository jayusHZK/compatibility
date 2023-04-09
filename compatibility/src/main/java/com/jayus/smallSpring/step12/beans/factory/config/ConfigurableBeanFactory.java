package com.jayus.smallSpring.step12.beans.factory.config;

import com.jayus.smallSpring.step12.beans.factory.HierarchicalBeanFactory;

/**
 * @Author: h zk
 * @Description: ex
 * @Date: 2023/4/9 18:17
 * @Version: 1.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE ="prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

}
