package com.jayus.smallSpring.step11.beans.factory.support;

import com.jayus.smallSpring.step11.core.io.DefaultResourceLoader;
import com.jayus.smallSpring.step11.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/4/6 11:48
 * @description :
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
