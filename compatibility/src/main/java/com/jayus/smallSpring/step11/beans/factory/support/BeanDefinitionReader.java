package com.jayus.smallSpring.step11.beans.factory.support;

import com.jayus.smallSpring.step11.beans.BeansException;
import com.jayus.smallSpring.step11.core.io.Resource;
import com.jayus.smallSpring.step11.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/4/6 11:44
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
