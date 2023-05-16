package com.jayus.smallSpring.step13.aop;

import java.lang.reflect.Method;

public interface MethodBeforeAdvice extends BeforeAdvice {

    void before(Method method,Object[] args,Object target) throws Throwable;

}
