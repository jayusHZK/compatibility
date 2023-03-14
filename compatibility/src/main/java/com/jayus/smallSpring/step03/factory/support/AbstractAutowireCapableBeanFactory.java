package com.jayus.smallSpring.step03.factory.support;

import com.jayus.smallSpring.step03.exception.BeanException;
import com.jayus.smallSpring.step03.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author: h zk
 * @Description: asdf
 * @Date: 2023/3/10 22:17
 * @Version: 1.0
 */
public class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeanException {

        return null;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (args != null && ctor.getParameterTypes().length == args.length){
                constructorToUse = ctor;
                break;
            }
        }
        // 通过cglib 创建对象
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructorToUse,args);
    }

    public InstantiationStrategy getInstantiationStrategy(){return instantiationStrategy;}

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition,beanName,args);
        } catch (Exception e) {
            throw new BeanException("Instantiation of bean failed",e);
        }
        addSingleton(beanName,bean);
        return bean;
    }
}