package com.jayus.smallSpring.step16.aop;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/7/14 10:04
 * @description :
 **/
public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method,Object[] args,Object target) throws Throwable;

}
