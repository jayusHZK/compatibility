package com.jayus.smallSpring.step06.context.support;

import com.jayus.smallSpring.step06.beans.BeansException;
import com.jayus.smallSpring.step06.beans.factory.ConfigurableListableBeanFactory;
import com.jayus.smallSpring.step06.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/3/15 11:14
 * @description :
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

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
