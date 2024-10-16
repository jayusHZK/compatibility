package com.jayus.smallMyBatis.step13.reflection.invoker;

/**
 * @ClassName Invoker
 * @Description: 调用者
 * @date: 2024/10/11 08:25
 */
public interface Invoker {

    Object invoke(Object target,Object[] args) throws Exception;

    Class<?> getType();

}
