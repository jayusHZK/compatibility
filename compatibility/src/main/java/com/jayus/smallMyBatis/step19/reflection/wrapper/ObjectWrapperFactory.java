package com.jayus.smallMyBatis.step19.reflection.wrapper;

import com.jayus.smallMyBatis.step19.reflection.MetaObject;

/**
 * @ClassName ObjectWrapperFactory
 * @Description: 对象包装工厂
 * @date: 2024/10/17 18:53
 */
public interface ObjectWrapperFactory {

    boolean hasWrapperFor(Object object);

    ObjectWrapper getWrapperFor(MetaObject metaObject,Object object);

}
