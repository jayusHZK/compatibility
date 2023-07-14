package com.jayus.smallSpring.step16.beans.factory.config;

import com.jayus.smallSpring.step16.beans.factory.HierarchicalBeanFactory;
import com.jayus.smallSpring.step16.util.StringValueResolver;

/**
 * @author : h zk
 * @date : 2023/7/14 11:38
 * @description :
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry{

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

}
