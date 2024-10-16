package com.jayus.smallMyBatis.step12.reflection.wrapper;

import com.jayus.smallMyBatis.step12.reflection.MetaObject;

/**
 * @ClassName ObjectWrapperFactory
 * @Description: 对象包装工厂
 * @date: 2024/9/20 06:32
 */
public interface ObjectWrapperFactory {

    /**
     * 判断有没有包装器
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject,Object object);

}
