package com.jayus.smallMyBatis.step13.reflection.wrapper;

import com.jayus.smallMyBatis.step13.reflection.MetaObject;

/**
 * @ClassName ObjectWrapperFactory
 * @Description: 对象包装工厂
 * @date: 2024/10/12 09:38
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
