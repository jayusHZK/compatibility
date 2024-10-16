package com.jayus.smallMyBatis.step12.reflection.wrapper;

import com.jayus.smallMyBatis.step12.reflection.MetaObject;

/**
 * @ClassName DefaultObjectWrapperFactory
 * @Description: 默认对象包装工厂
 * @date: 2024/9/20 06:48
 */
public class DefaultObjectWrapperFactory implements ObjectWrapperFactory {

    @Override
    public boolean hasWrapperFor(Object object) {
        return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        throw new RuntimeException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }
}
