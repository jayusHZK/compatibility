package com.jayus.smallSpring.step17.aop;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/8/1 15:22
 * @description :
 **/
public interface MethodBeforeAdvice extends BeforeAdvice {

    void before(Method method,Object[] args,Object target) throws Throwable;

}
