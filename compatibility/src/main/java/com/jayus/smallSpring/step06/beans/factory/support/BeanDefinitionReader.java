package com.jayus.smallSpring.step06.beans.factory.support;

import com.jayus.smallSpring.step06.beans.BeansException;
import com.jayus.smallSpring.step06.core.io.Resource;
import com.jayus.smallSpring.step06.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/3/14 18:01
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
