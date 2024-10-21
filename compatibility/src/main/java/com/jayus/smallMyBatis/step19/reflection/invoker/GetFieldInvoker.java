package com.jayus.smallMyBatis.step19.reflection.invoker;

import java.lang.reflect.Field;

/**
 * @ClassName GetFieldInvoker
 * @Description: getter 调用者
 * @date: 2024/10/16 21:33
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
