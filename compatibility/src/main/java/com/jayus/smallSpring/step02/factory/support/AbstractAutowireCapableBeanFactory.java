package com.jayus.smallSpring.step02.factory.support;

import com.jayus.smallSpring.step02.exception.BeanException;
import com.jayus.smallSpring.step02.factory.config.BeanDefinition;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/8 23:02
 * @Version: 1.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanException {
        Object bean;
        try {
            // 插件 定义bean 的实例
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanException("Instantiation of bean failed",e);
        }
        // 添加 bean 进单例容器
        addSingleton(beanName,bean);
        return bean;
    }
}