package com.jayus.smallMyBatis.step12.reflection.invoker;

import java.lang.reflect.Field;

/**
 * @ClassName GetterFieldInvoker
 * @Description: getter 调用者
 * @date: 2024/9/19 20:46
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
