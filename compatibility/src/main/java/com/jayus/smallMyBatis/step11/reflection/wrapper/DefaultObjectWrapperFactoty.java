package com.jayus.smallMyBatis.step11.reflection.wrapper;

import com.jayus.smallMyBatis.step11.reflection.MetaObject;

/**
 * @ClassName DefaultObjectWrapperFactoty
 * @Description: 默认对象包装工厂
 * @date: 2024/7/24 10:04
 */
public class DefaultObjectWrapperFactoty implements ObjectWrapperFactory {

    @Override
    public boolean hasWrapperFor(Object object) {
        return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        throw new RuntimeException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }
}
