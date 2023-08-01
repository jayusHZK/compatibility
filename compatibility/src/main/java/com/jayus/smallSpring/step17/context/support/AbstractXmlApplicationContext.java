package com.jayus.smallSpring.step17.context.support;

import com.jayus.smallSpring.step17.beans.factory.support.DefaultListableBeanFactory;
import com.jayus.smallSpring.step17.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author : h zk
 * @date : 2023/8/1 11:40
 * @description :
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplication {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinition(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
