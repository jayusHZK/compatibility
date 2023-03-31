package com.jayus.smallSpring.step10.beans.factory.config;

import com.jayus.smallSpring.step10.beans.BeansException;
import com.jayus.smallSpring.step10.beans.factory.BeanFactory;

/**
 * @author : h zk
 * @date : 2023/3/31 15:56
 * @description :
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName);

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessAfterInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName)throws BeansException;

}
