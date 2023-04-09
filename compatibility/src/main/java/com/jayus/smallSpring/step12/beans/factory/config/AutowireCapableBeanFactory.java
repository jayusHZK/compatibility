package com.jayus.smallSpring.step12.beans.factory.config;

import com.jayus.smallSpring.step12.beans.factory.BeanFactory;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 19:09
 * @Version: 1.0
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorBeforeInitialization(Object existingBean,String beanName);

    /**
     * 执行 BeanPostProcessors 接口实现类的额 postProcessorsAfterInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanProcessorsAfterInitialization(Object existingBean,String beanName);

}
