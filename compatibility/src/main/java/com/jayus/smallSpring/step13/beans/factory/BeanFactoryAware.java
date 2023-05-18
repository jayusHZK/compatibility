package com.jayus.smallSpring.step13.beans.factory;

import com.jayus.smallSpring.step13.beans.BeansException;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
