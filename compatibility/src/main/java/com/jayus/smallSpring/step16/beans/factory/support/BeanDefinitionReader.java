package com.jayus.smallSpring.step16.beans.factory.support;

import com.jayus.smallSpring.step16.beans.BeansException;
import com.jayus.smallSpring.step16.core.io.Resource;
import com.jayus.smallSpring.step16.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/7/14 14:35
 * @description :
 **/
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;

}
