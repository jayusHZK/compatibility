package com.jayus.smallMyBatis.step07.reflection.wrapper;

import com.jayus.smallMyBatis.step07.reflection.MetaObject;

/**
 * 对象包装器工厂
 */
public interface ObjectWrapperFactory {

    // 是否有包装器
    boolean hasWrapperFor(Object object);

    // 获取包装器
    ObjectWrapper getWrapperFor(MetaObject metaObject,Object object);
}
