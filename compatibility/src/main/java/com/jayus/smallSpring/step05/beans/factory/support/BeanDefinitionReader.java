package com.jayus.smallSpring.step05.beans.factory.support;

import com.jayus.smallSpring.step05.beans.BeansException;
import com.jayus.smallSpring.step05.core.io.Resource;
import com.jayus.smallSpring.step05.core.io.ResourceLoader;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/12 21:44
 * @Version: 1.0
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResoutceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

}
