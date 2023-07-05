package com.jayus.smallSpring.step15.aop;

/**
 * @author : h zk
 * @date : 2023/7/5 9:55
 * @description :
 **/
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
