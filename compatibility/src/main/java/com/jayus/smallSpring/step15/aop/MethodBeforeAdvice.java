package com.jayus.smallSpring.step15.aop;

import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/7/5 9:50
 * @description :
 **/
public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method, Object[] args,Object target) throws Throwable;

}
