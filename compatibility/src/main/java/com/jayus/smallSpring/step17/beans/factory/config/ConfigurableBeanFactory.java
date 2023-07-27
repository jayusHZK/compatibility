package com.jayus.smallSpring.step17.beans.factory.config;

import com.jayus.smallSpring.step17.beans.factory.HierarchicalBeanFactory;
import com.jayus.smallSpring.step17.core.convert.ConversionService;
import com.jayus.smallSpring.step17.util.StringValueResolver;

import javax.annotation.Nullable;

/**
 * @author : h zk
 * @date : 2023/7/27 11:29
 * @description :
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

    void setConversionService(ConversionService conversionService);

    @Nullable
    ConversionService getConversionService();

}
