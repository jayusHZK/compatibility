package com.jayus.smallMyBatis.step13.reflection.invoker;

import java.lang.reflect.Method;

/**
 * @ClassName MethodInvoker
 * @Description: 方法调用者
 * @date: 2024/10/11 08:27
 */
public class MethodInvoker implements Invoker {

    private Class<?> type;

    private Method method;

    public MethodInvoker(Method method) {
        this.method = method;
        if (method.getParameterTypes().length == 1) {
            type = method.getParameterTypes()[0];
        } else {
            type = method.getReturnType();
        }
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        return method.invoke(target,args);
    }

    @Override
    public Class<?> getType() {
        return type;
    }
}
