package com.jayus.smallSpring.step11.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.jayus.smallSpring.step11.beans.BeansException;
import com.jayus.smallSpring.step11.beans.factory.DisposableBean;
import com.jayus.smallSpring.step11.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/5 22:59
 * @Version: 1.0
 */
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
        // 实现接口
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        // 注解配置 desttoy-method (判断是为了避免二次执行销毁)
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod){
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}