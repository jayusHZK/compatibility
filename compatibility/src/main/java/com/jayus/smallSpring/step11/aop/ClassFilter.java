package com.jayus.smallSpring.step11.aop;

/**
 * @author : h zk
 * @date : 2023/4/4 11:44
 * @description :
 **/
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
