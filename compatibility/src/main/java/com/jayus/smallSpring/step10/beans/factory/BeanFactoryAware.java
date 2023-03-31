package com.jayus.smallSpring.step10.beans.factory;

import com.jayus.smallSpring.step10.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/31 15:18
 * @description : 实现此接口，即能感知到所属的 BeanFactory
 **/
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
