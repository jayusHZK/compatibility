package com.jayus.smallSpring.step12.context.support;

import com.jayus.smallSpring.step12.beans.factory.support.DefaultListableBeanFactory;
import com.jayus.smallSpring.step12.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author : h zk
 * @date : 2023/5/4 18:09
 * @description :
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinition(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null){
            beanDefinitionReader.loadBeanDefinition(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
