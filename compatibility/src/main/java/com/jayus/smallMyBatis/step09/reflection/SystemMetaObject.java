package com.jayus.smallMyBatis.step09.reflection;

import com.jayus.smallMyBatis.step09.reflection.factory.DefaultObjectFactory;
import com.jayus.smallMyBatis.step09.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step09.reflection.wrapper.DefaultObjectWrapperFactory;
import com.jayus.smallMyBatis.step09.reflection.wrapper.ObjectWrapperFactory;

/**
 * 系统元对象
 */
public class SystemMetaObject {

    // 默认对象工厂
    public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();

    // 默认对象包装工厂
    public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY= new DefaultObjectWrapperFactory();

    public static final MetaObject NULL_META_OBJECT= MetaObject.forObject(NullObject.class,DEFAULT_OBJECT_FACTORY,DEFAULT_OBJECT_WRAPPER_FACTORY);

    public SystemMetaObject() {
    }

    public static class NullObject{

    }

    /**
     * 获取元对象
     * @param object
     * @return
     */
    public static MetaObject forObject(Object object){
        return MetaObject.forObject(object,DEFAULT_OBJECT_FACTORY,DEFAULT_OBJECT_WRAPPER_FACTORY);
    }
}
