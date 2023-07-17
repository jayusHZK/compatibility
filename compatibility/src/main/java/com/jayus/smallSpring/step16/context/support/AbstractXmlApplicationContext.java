package com.jayus.smallSpring.step16.context.support;

import com.jayus.smallSpring.step16.beans.factory.support.DefaultListableBeanFactory;
import com.jayus.smallSpring.step16.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author : h zk
 * @date : 2023/7/17 16:32
 * @description :
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocation = getConfigLocation();
        if (null != configLocation){
            beanDefinitionReader.loadBeanDefinitions(configLocation);
        }
    }

    protected abstract String[] getConfigLocation();

}
