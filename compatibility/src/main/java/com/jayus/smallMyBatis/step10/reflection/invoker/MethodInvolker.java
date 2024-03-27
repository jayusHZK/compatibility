package com.jayus.smallMyBatis.step10.reflection.invoker;

import java.lang.reflect.Method;

/**
 * 方法调用者
 */
public class MethodInvolker implements Invoker{

    private Class<?> type;

    private Method method;

    public MethodInvolker(Method method) {
        this.method = method;
        if (method.getParameterTypes().length == 1){
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
