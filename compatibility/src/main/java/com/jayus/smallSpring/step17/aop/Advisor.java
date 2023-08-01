package com.jayus.smallSpring.step17.aop;

import org.aopalliance.aop.Advice;

/**
 * @author : h zk
 * @date : 2023/8/1 15:21
 * @description :
 **/
public interface Advisor {

    Advice getAdvice();

}
