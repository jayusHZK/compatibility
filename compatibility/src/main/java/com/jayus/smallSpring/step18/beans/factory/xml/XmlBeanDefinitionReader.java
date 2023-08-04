package com.jayus.smallSpring.step18.beans.factory.xml;

import com.jayus.smallSpring.step18.beans.factory.support.AbstractBeanDefinitionReader;
import com.jayus.smallSpring.step18.beans.factory.support.BeanDefinitionRegistry;
import com.jayus.smallSpring.step18.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/8/4 17:23
 * @description :
 **/
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }



}
