package com.jayus.smallMyBatis.step19.reflection.wrapper;

import com.jayus.smallMyBatis.step19.reflection.MetaObject;

/**
 * @ClassName DefaultObjectWrapperFactory
 * @Description: 默认对象包装工厂
 * @date: 2024/10/17 18:56
 */
public class DefaultObjectWrapperFactory implements ObjectWrapperFactory{

    @Override
    public boolean hasWrapperFor(Object object) {
        return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        throw new RuntimeException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }
}
