package com.jayus.smallMyBatis.step11.reflection;

import com.jayus.smallMyBatis.step11.reflection.factory.DefaultObjectFactory;
import com.jayus.smallMyBatis.step11.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step11.reflection.wrapper.DefaultObjectWrapperFactoty;
import com.jayus.smallMyBatis.step11.reflection.wrapper.ObjectWrapperFactory;

/**
 * @ClassName SystemMetaObject
 * @Description: 一些系统级别的元对象
 * @date: 2024/7/24 10:01
 */
public class SystemMetaObject {

    public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();

    public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactoty();

    public static final MetaObject NULL_META_OBJECT = MetaObject.forObject(NullObject.class,DEFAULT_OBJECT_FACTORY,DEFAULT_OBJECT_WRAPPER_FACTORY);

    public SystemMetaObject() {
    }

    private static class NullObject{

    }

    public static MetaObject forObject(Object object) {
        return MetaObject.forObject(object,DEFAULT_OBJECT_FACTORY,DEFAULT_OBJECT_WRAPPER_FACTORY);
    }

}
