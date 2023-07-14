package com.jayus.smallSpring.step16.beans.factory.support;

import com.jayus.smallSpring.step16.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step16.core.io.DefaultResourceLoader;
import com.jayus.smallSpring.step16.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/7/14 17:17
 * @description :
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
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
