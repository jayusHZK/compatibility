package com.jayus.smallSpring.step14.aop;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/6/21 9:55
 * @description :
 **/
public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method,Object[] args,Object target) throws Throwable;

}
