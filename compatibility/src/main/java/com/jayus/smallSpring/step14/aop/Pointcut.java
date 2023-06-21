package com.jayus.smallSpring.step14.aop;

/**
 * @author : h zk
 * @date : 2023/6/21 9:57
 * @description :
 **/
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
