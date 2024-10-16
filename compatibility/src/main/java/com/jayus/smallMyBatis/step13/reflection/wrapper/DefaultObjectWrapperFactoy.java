package com.jayus.smallMyBatis.step13.reflection.wrapper;

import com.jayus.smallMyBatis.step13.reflection.MetaObject;

/**
 * @ClassName DefaultObjectWrapperFactoy
 * @Description: 默认对象包装工厂
 * @date: 2024/10/12 09:47
 */
public class DefaultObjectWrapperFactoy implements ObjectWrapperFactory {

    @Override
    public boolean hasWrapperFor(Object object) {
        return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        throw new RuntimeException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }
}
