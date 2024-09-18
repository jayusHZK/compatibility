package com.jayus.smallMyBatis.step11.reflection.wrapper;

import com.jayus.smallMyBatis.step11.reflection.MetaObject;

/**
 * @ClassName ObjectWrapperFactory
 * @Description: 对象包装工厂
 * @date: 2024/7/24 09:31
 */
public interface ObjectWrapperFactory {

    // 判断有没有包装器
    boolean hasWrapperFor(Object object);

    // 得到包装器
    ObjectWrapper getWrapperFor(MetaObject metaObject,Object object);
}
