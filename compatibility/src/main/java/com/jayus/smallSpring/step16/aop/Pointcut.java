package com.jayus.smallSpring.step16.aop;

/**
 * @author : h zk
 * @date : 2023/7/14 10:01
 * @description :
 **/
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
