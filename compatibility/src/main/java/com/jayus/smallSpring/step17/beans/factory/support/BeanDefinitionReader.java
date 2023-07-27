package com.jayus.smallSpring.step17.beans.factory.support;

import com.jayus.smallSpring.step17.beans.BeansException;
import com.jayus.smallSpring.step17.core.io.Resource;
import com.jayus.smallSpring.step17.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/7/27 18:26
 * @description :
 **/
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinition(Resource resource) throws BeansException;

    void loadBeanDefinition(Resource... resources) throws BeansException;

    void loadBeanDefinition(String location) throws BeansException;

    void loadBeanDefinition(String... locations) throws BeansException;

}
