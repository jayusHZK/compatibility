package com.jayus.smallSpring.step18.beans.factory.config;

import com.jayus.smallSpring.step18.beans.factory.HierarchicalBeanFactory;
import com.jayus.smallSpring.step18.core.convert.ConversionService;
import com.jayus.smallSpring.step18.util.StringValueResolver;

/**
 * @author : h zk
 * @date : 2023/8/3 18:08
 * @description :
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

    void setConversionService(ConversionService conversionService);

    ConversionService getConversionService();

}
