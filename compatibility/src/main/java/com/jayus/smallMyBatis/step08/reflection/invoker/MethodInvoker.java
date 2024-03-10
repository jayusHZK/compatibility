package com.jayus.smallMyBatis.step08.reflection.invoker;

import java.lang.reflect.Method;

/**
 * 方法调用器
 */
public class MethodInvoker implements Invoker {

    private Class<?> type;

    private Method method;

    public MethodInvoker(Method method) {
        this.method = method;
        // 对于实体类而言 如果只有一个参数 那么就是 set 方法 类型就是方法参数
        if (method.getParameterTypes().length == 1){
            this.type = method.getParameterTypes()[0];
        } else {
            // 否则就是 get 方法 类型就是方法返回值信息
            this.type = method.getReturnType();
        }
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        return method.invoke(target,args);
    }

    @Override
    public Class<?> getType() {
        return this.type;
    }
}
