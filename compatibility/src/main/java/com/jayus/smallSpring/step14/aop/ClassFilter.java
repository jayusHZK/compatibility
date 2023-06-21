package com.jayus.smallSpring.step14.aop;

/**
 * @author : h zk
 * @date : 2023/6/21 9:53
 * @description :
 **/
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
