package com.jayus.smallMyBatis.step10.reflection.wrapper;

import com.jayus.smallMyBatis.step10.reflection.MetaObject;

import java.util.Collection;

/**
 * Collection 包装器
 */
public class CollectionWrapper implements ObjectWrapper {

    private Collection<Object> object;

    public CollectionWrapper(MetaObject metaObject,Collection<Object> object) {
        this.object = object;
    }


}
