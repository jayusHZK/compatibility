package com.jayus.smallSpring.step07.beans.factory.config;

import com.jayus.smallSpring.step07.beans.BeansException;
import com.jayus.smallSpring.step07.beans.factory.BeanFactory;

/**
 * @author : h zk
 * @date : 2023/3/17 14:49
 * @description :
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     *  执行 BeanPostProcessors 接口实现类的 PostProcessBeforeInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorBeforeInitialization(Object existingBean,String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的postProcessorsAfterInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitializetion(Object existingBean,String beanName) throws BeansException;

}
