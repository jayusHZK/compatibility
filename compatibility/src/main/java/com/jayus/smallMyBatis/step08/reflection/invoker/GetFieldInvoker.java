package com.jayus.smallMyBatis.step08.reflection.invoker;

import java.lang.reflect.Field;

/**
 * 获取字段 getter 调用器
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
