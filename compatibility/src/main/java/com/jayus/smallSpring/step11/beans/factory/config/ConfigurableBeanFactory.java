package com.jayus.smallSpring.step11.beans.factory.config;

import com.jayus.smallSpring.step11.beans.factory.HierarchicalBeanFactory;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/5 22:13
 * @Version: 1.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例方法
     */
    void destroySingletons();


}
