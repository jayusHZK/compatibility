package com.jayus.smallSpring.step14.context.support;

import com.jayus.smallSpring.step14.beans.BeansException;
import com.jayus.smallSpring.step14.beans.factory.ConfigurableListableBeanFactory;
import com.jayus.smallSpring.step14.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/6/27 10:39
 * @description :
 **/
public abstract class abstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory(){
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
