package com.jayus.smallSpring.step09.context.support;

import com.jayus.smallSpring.step09.beans.factory.support.DefaultListableBeanFactory;
import com.jayus.smallSpring.step09.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author : h zk
 * @date : 2023/3/29 15:17
 * @description :
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinition(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();

}
