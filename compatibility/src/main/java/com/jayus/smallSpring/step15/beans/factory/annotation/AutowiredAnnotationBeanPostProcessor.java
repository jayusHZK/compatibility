package com.jayus.smallSpring.step15.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import com.jayus.smallSpring.step15.beans.BeansException;
import com.jayus.smallSpring.step15.beans.PropertyValues;
import com.jayus.smallSpring.step15.beans.factory.BeanFactory;
import com.jayus.smallSpring.step15.beans.factory.BeanFactoryAware;
import com.jayus.smallSpring.step15.beans.factory.ConfigurableListableBeanFactory;
import com.jayus.smallSpring.step15.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.jayus.smallSpring.step15.util.ClassUtils;
import org.openjdk.jol.info.FieldData;

import javax.print.attribute.standard.Fidelity;
import java.lang.reflect.Field;

/**
 * @author : h zk
 * @date : 2023/7/6 11:03
 * @description :
 **/
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFacotory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation){
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
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
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
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

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }
}
