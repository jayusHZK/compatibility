package com.jayus.smallMyBatis.step13.reflection.invoker;

import java.lang.reflect.Field;

/**
 * @ClassName GetFieldInvoker
 * @Description: getter调用者
 * @date: 2024/10/11 08:25
 */
public class GetFieldInvoker implements Invoker {

    private Field field;

    public GetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        return field.get(target);
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
