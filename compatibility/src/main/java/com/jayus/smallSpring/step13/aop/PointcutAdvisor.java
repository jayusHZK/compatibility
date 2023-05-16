package com.jayus.smallSpring.step13.aop;

public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}
