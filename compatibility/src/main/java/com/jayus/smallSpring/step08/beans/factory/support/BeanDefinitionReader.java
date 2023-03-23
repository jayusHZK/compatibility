package com.jayus.smallSpring.step08.beans.factory.support;

import com.jayus.smallSpring.step08.beans.BeansException;
import com.jayus.smallSpring.step08.core.io.Resource;
import com.jayus.smallSpring.step08.core.io.ResourceLoader;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/23 23:40
 * @Version: 1.0
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;

}
