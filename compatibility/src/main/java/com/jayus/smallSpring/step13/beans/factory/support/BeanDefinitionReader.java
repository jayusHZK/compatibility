package com.jayus.smallSpring.step13.beans.factory.support;

import com.jayus.smallSpring.step13.beans.BeansException;
import com.jayus.smallSpring.step13.core.io.Resource;
import com.jayus.smallSpring.step13.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegisty();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;

}
