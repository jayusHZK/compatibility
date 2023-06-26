package com.jayus.smallSpring.step14.beans.factory.config;

import com.jayus.smallSpring.step14.beans.factory.HierarchicalBeanFactory;
import com.jayus.smallSpring.step14.util.StringValueResolver;

/**
 * @author : h zk
 * @date : 2023/6/21 14:30
 * @description :
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destorySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

}
