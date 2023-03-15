package com.jayus.smallSpring.step06.context.support;

import com.jayus.smallSpring.step06.beans.factory.support.DefaultListableBeanFactory;
import com.jayus.smallSpring.step06.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author : h zk
 * @date : 2023/3/15 11:28
 * @description :
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

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
