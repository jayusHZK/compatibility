package com.jayus.smallSpring.step12.beans.factory;

import com.jayus.smallSpring.step12.beans.BeansException;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 18:10
 * @Version: 1.0
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
