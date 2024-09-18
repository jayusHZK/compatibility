package com.jayus.smallMyBatis.step11.reflection.invoker;

import java.lang.reflect.Field;

/**
 * @ClassName GetFieldInvoker
 * @Description: getter 调用者
 * @date: 2024/5/14 15:25
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
