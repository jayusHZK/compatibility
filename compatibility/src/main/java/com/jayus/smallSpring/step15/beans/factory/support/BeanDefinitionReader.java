package com.jayus.smallSpring.step15.beans.factory.support;

import com.jayus.smallSpring.step15.beans.BeansException;
import com.jayus.smallSpring.step15.core.io.Resource;
import com.jayus.smallSpring.step15.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/7/6 14:32
 * @description :
 **/
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... location) throws BeansException;

}
