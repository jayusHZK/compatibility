package com.jayus.smallSpring.step07.context.support;

import com.jayus.smallSpring.step07.beans.factory.support.DefaultListableBeanFactory;
import com.jayus.smallSpring.step07.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/19 22:47
 * @Version: 1.0
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();

}