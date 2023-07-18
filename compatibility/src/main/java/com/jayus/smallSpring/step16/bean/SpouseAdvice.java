package com.jayus.smallSpring.step16.bean;

import com.jayus.smallSpring.step16.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/7/18 10:35
 * @description :
 **/
public class SpouseAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("关怀小两口(切面)："+method);
    }
}
