package com.jayus.smallSpring.step11.context.support;

import com.jayus.smallSpring.step11.beans.BeansException;
import com.jayus.smallSpring.step11.beans.factory.ConfigurableListenableBeanFactory;
import com.jayus.smallSpring.step11.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author : h zk
 * @date : 2023/4/6 19:08
 * @description :
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {

    }

    private DefaultListableBeanFactory createBeanFactory(){
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListenableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
