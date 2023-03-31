package com.jayus.smallSpring.step10.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.jayus.smallSpring.step10.beans.BeansException;
import com.jayus.smallSpring.step10.beans.factory.DisposableBean;
import com.jayus.smallSpring.step10.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/3/31 16:16
 * @description :
 **/
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestoryMethodName();
    }

    @Override
    public void destory() throws Exception {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destory();
        }
        if (StrUtil.isNotEmpty(destroyMethodName) && (bean instanceof DisposableBean && "destory".equals(destroyMethodName))) {
            Method destoryMethod = bean.getClass().getMethod(destroyMethodName);
            if (destoryMethod == null){
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destoryMethod.invoke(bean);
        }
    }
}
