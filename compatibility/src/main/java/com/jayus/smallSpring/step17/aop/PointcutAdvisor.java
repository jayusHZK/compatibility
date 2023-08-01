package com.jayus.smallSpring.step17.aop;

/**
 * @author : h zk
 * @date : 2023/8/1 15:25
 * @description :
 **/
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}
