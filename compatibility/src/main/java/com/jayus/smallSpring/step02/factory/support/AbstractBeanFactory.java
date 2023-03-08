package com.jayus.smallSpring.step02.factory.support;

import com.jayus.smallSpring.step02.exception.BeanException;
import com.jayus.smallSpring.step02.factory.BeanFactory;
import com.jayus.smallSpring.step02.factory.config.BeanDefinition;

/**
 * @Author: h zk
 * @Description: 注册表接口
 * @Date: 2023/3/8 23:03
 * @Version: 1.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry  implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeanException {
        Object bean = getSingleton(name);
        if (bean != null){
            return bean;
        }
        BeanDefinition beanDefinition = getbeanDefinition(name);
        // 此处会调用具体实现类的方法
        return createBean(name,beanDefinition);
    }

    protected abstract BeanDefinition getbeanDefinition(String beanName)
        throws BeanException;

    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition)
        throws BeanException;
}