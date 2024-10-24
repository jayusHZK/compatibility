package com.jayus.smallMyBatis.step12.reflection.invoker;

import java.lang.reflect.Field;

/**
 * @ClassName SetFieldInvoker
 * @Description: setter 调用者
 * @date: 2024/9/19 20:45
 */
public class SetFieldInvoker implements Invoker {

    private Field field;

    public SetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        field.set(target,args[0]);
        return null;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
