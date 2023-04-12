package com.jayus.smallSpring.step12.bean;

import com.jayus.smallSpring.step12.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/4/12 15:32
 * @description :
 **/
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法:" + method.getName());
    }
}
