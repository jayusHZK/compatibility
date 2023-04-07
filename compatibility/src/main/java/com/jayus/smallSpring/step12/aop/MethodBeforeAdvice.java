package com.jayus.smallSpring.step12.aop;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/4/7 15:02
 * @description :
 **/
public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method,Object[] args,Object target)throws Throwable;

}
