package com.jayus.smallSpring.step07.beans.factory.support;

import com.jayus.smallSpring.step07.beans.BeansException;
import com.jayus.smallSpring.step07.beans.factory.config.BeanDefinition;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @author : h zk
 * @date : 2023/3/17 15:24
 * @description :
 **/
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (ctor == null) return enhancer.create();
        return enhancer.create(ctor.getParameterTypes(),args);
    }
}
