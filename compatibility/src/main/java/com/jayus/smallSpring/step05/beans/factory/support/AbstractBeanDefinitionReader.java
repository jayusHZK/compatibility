package com.jayus.smallSpring.step05.beans.factory.support;

import com.jayus.smallSpring.step05.core.io.DefaultResourceLoader;
import com.jayus.smallSpring.step05.core.io.ResourceLoader;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/12 22:34
 * @Version: 1.0
 */
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
    public ResourceLoader getResoutceLoader() {
        return resourceLoader;
    }
}