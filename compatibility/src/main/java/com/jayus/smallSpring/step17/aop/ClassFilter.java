package com.jayus.smallSpring.step17.aop;

/**
 * @author : h zk
 * @date : 2023/8/1 15:22
 * @description :
 **/
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
