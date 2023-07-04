package com.jayus.smallSpring.step14.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import com.jayus.smallSpring.step14.beans.BeansException;
import com.jayus.smallSpring.step14.beans.PropertyValues;
import com.jayus.smallSpring.step14.beans.factory.BeanFactory;
import com.jayus.smallSpring.step14.beans.factory.BeanFactoryAware;
import com.jayus.smallSpring.step14.beans.factory.ConfigurableListableBeanFactory;
import com.jayus.smallSpring.step14.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.jayus.smallSpring.step14.util.ClassUtils;

import javax.print.attribute.standard.Fidelity;
import java.lang.reflect.Field;

/**
 * @author : h zk
 * @date : 2023/6/21 14:29
 * @description :
 **/
public class AutowiredAnnotationBeanProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 处理value 注解
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;

        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation) {
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
        }
        // 处理 autowired 注解
        for (Field field : declaredFields) {
            Autowired annotationAnnotation = field.getAnnotation(Autowired.class);
            if (null != annotationAnnotation) {
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (null != qualifierAnnotation) {
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName,fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean,field.getName(),dependentBean);
            }
        }
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }
}
