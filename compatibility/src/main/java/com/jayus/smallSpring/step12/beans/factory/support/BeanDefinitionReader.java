package com.jayus.smallSpring.step12.beans.factory.support;

import com.jayus.smallSpring.step12.beans.BeansException;
import com.jayus.smallSpring.step12.core.io.Resource;
import com.jayus.smallSpring.step12.core.io.ResourceLoader;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 23:26
 * @Version: 1.0
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinition(Resource resource) throws BeansException;

    void loadBeanDefinition(Resource... resources) throws BeansException;

    void loadBeanDefinition(String location) throws BeansException;

    void loadBeanDefinition(String... locations) throws BeansException;

}
