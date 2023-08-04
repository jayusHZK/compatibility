package com.jayus.smallSpring.step18.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.jayus.smallSpring.step18.beans.BeansException;
import com.jayus.smallSpring.step18.beans.factory.DisposableBean;
import com.jayus.smallSpring.step18.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/8/4 10:22
 * @description :
 **/
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destory".equals(this.destroyMethodName))){
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (destroyMethod == null){
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
