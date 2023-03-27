package com.jayus.smallSpring.step08.context.support;

import com.jayus.smallSpring.step08.beans.factory.support.DefaultListableBeanFactory;
import com.jayus.smallSpring.step08.beans.factory.xml.XmlBeanDefinitionReader;

/**
* @author : h zk
* @date : 2023/3/27 15:36
* @description : 
**/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
