package com.jayus.smallSpring.step16.aop;

import org.aopalliance.aop.Advice;

/**
 * @author : h zk
 * @date : 2023/7/14 9:59
 * @description :
 **/
public interface Advisor {

    Advice getAdvice();

}
