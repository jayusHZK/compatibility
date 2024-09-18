package com.jayus.smallMyBatis.step11.reflection.invoker;

/**
 * @ClassName Invoker
 * @Description: 调用者
 * @date: 2024/5/14 15:20
 */
public interface Invoker {

    Object invoke(Object target,Object[] args) throws Exception;

    Class<?> getType();

}
