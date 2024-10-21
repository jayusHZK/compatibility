package com.jayus.smallMyBatis.step19.reflection.invoker;

import java.lang.reflect.Field;

/**
 * @ClassName SetFieldInvoker
 * @Description: setter 调用者
 * @date: 2024/10/16 21:36
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
