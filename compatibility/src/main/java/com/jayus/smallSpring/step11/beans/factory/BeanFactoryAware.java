package com.jayus.smallSpring.step11.beans.factory;

import com.jayus.smallSpring.step11.beans.BeansException;

/**
 * @Author: h zk
 * @Description: 实现此接口，即能感知到所属的 BeanFactory
 * @Date: 2023/4/5 21:58
 * @Version: 1.0
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
