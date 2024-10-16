package com.jayus.smallMyBatis.step13.reflection.invoker;

import java.lang.reflect.Field;

/**
 * @ClassName SetFieldInvoker
 * @Description: setter 调用者
 * @date: 2024/10/11 08:26
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
