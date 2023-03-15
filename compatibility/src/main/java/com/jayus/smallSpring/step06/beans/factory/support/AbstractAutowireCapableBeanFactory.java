package com.jayus.smallSpring.step06.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.jayus.smallSpring.step06.beans.BeansException;
import com.jayus.smallSpring.step06.beans.PropertyValue;
import com.jayus.smallSpring.step06.beans.PropertyValues;
import com.jayus.smallSpring.step06.beans.factory.config.AutowireCapableBeanFactory;
import com.jayus.smallSpring.step06.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step06.beans.factory.config.BeanPostProcessor;
import com.jayus.smallSpring.step06.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * @author : h zk
 * @date : 2023/3/14 19:39
 * @description :
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean= createBeanInstance(beanDefinition,name,args);
            applyPropertyValues(name,bean,beanDefinition);
            bean = initializeBean(name,bean,beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args) {
        Constructor constructorToUse = null;
        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (args != null && ctor.getParameterTypes().length == args.length){
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructorToUse,args);
    }

    protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean,name,value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    private Object initializeBean(String beanName,Object bean,BeanDefinition beanDefinition){
        // 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);
        // 待完成内容 invokeInitMethods
        invokeInitMethods(beanName,wrappedBean,beanDefinition);
        wrappedBean = applyBeanPostProcessorBeforeInitialization(wrappedBean,beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName,Object wrappedBean,BeanDefinition beanDefinition){

    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }
}
