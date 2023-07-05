package com.jayus.smallSpring.step15.aop;

/**
 * @author : h zk
 * @date : 2023/7/5 9:57
 * @description :
 **/
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();

}
