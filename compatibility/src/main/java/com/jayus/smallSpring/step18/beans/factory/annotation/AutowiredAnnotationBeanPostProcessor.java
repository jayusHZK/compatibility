package com.jayus.smallSpring.step18.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.TypeUtil;
import com.jayus.smallSpring.step18.beans.BeansException;
import com.jayus.smallSpring.step18.beans.PropertyValues;
import com.jayus.smallSpring.step18.beans.factory.BeanFactory;
import com.jayus.smallSpring.step18.beans.factory.BeanFactoryAware;
import com.jayus.smallSpring.step18.beans.factory.config.ConfigurableBeanFactory;
import com.jayus.smallSpring.step18.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.jayus.smallSpring.step18.core.convert.ConversionService;
import com.jayus.smallSpring.step18.util.ClassUtils;

import java.lang.reflect.Field;

/**
 * @author : h zk
 * @date : 2023/8/3 18:05
 * @description :
 **/
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    
    private ConfigurableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ?clazz.getSuperclass():clazz;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation){
                Object value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue((String) value);
                Class<?> sourceType = value.getClass();
                Class<?> targetType = (Class<?>) TypeUtil.getType(field);
                ConversionService conversionService = beanFactory.getConversionService();
                if (conversionService != null){
                    if (conversionService.canConvert(sourceType,targetType)){
                        value = conversionService.convert(value,targetType);
                    }
                }
                BeanUtil.setFieldValue(bean,field.getName(),value);
            }
        }

        for (Field field : declaredFields) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (null != autowiredAnnotation){
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (null != qualifierAnnotation){
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
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
