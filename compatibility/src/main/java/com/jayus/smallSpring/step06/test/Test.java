package com.jayus.smallSpring.step06.test;


import com.jayus.smallSpring.step06.beans.factory.support.DefaultListableBeanFactory;
import com.jayus.smallSpring.step06.beans.factory.xml.XmlBeanDefinitionReader;
import com.jayus.smallSpring.step06.common.MyBeanFactoryPostProcessor;
import com.jayus.smallSpring.step06.common.MyBeanPostProcessor;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/14 23:15
 * @Version: 1.0
 */
public class Test {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

    }

}