package com.jayus.smallSpring.step14.context.support;

import com.jayus.smallSpring.step14.beans.factory.support.DefaultListableBeanFactory;
import com.jayus.smallSpring.step14.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author : h zk
 * @date : 2023/6/27 10:43
 * @description :
 **/
public abstract class AbstractXmlApplicationContext extends abstractRefreshableApplicationContext{

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
