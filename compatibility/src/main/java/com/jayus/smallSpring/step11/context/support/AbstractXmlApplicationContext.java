package com.jayus.smallSpring.step11.context.support;

import com.jayus.smallSpring.step11.beans.factory.support.DefaultListableBeanFactory;
import com.jayus.smallSpring.step11.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/6 23:09
 * @Version: 1.0
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null){
            beanDefinitionReader.loadBeanDefinition(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}