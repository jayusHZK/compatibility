package com.jayus.smallMyBatis.step10.reflection.wrapper;

import com.jayus.smallMyBatis.step10.reflection.MetaObject;

/**
 * 对象保证工厂
 */
public interface ObjectWrapperFactory {

    /**
     * 判断有没有包装器
     * @param object
     * @return
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     * @param metaObject
     * @param object
     * @return
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject,Object object);

}
