package com.jayus.smallMyBatis.step12.reflection.invoker;

/**
 * @ClassName Invoker
 * @Description: 调用者
 * @date: 2024/9/19 19:38
 */
public interface Invoker {

    Object invoke(Object target,Object[] args) throws Exception;

    Class<?> getType();

}
