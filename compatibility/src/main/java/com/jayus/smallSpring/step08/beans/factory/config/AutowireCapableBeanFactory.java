package com.jayus.smallSpring.step08.beans.factory.config;

import com.jayus.smallSpring.step08.beans.BeansException;
import com.jayus.smallSpring.step08.beans.factory.BeanFactory;

/**
 * @author : h zk
 * @date : 2023/3/23 19:42
 * @description :
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     * @param exisitingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanProcessorsAfterInitialization(Object exisitingBean,String beanName) throws BeansException;

}
