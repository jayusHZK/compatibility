
package com.jayus.smallMyBatis.step10.reflection;

import com.jayus.smallMyBatis.step10.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step10.reflection.property.PropertyTokenizer;
import com.jayus.smallMyBatis.step10.reflection.wrapper.ObjectWrapper;
import com.jayus.smallMyBatis.step10.reflection.wrapper.ObjectWrapperFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 元对象
 */
public class MetaObject {

    // 原对象
    private Object originalObject;

    // 对象包装器
    private ObjectWrapper objectWrapper;

    // 对象工厂
    private ObjectFactory objectFactory;

    // 对象包装工厂
    private ObjectWrapperFactory objectWrapperFactory;

    public MetaObject(Object originalObject, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory) {
        this.originalObject = originalObject;
        this.objectFactory = objectFactory;
        this.objectWrapperFactory = objectWrapperFactory;

        if (originalObject instanceof ObjectWrapper) {
            // 如果对象本身已经是ObjectWrapper型，则直接赋给objectWrapper
            this.objectWrapper = (ObjectWrapper) originalObject;
        } else if (objectWrapperFactory.hasWrapperFor(originalObject)) {
            // 如果有包装器,调用ObjectWrapperFactory.getWrapperFor
            this.objectWrapper = objectWrapperFactory.getWrapperFor(this, originalObject);
        } else if (originalObject instanceof Map) {
            // 如果是Map型，返回MapWrapper
            this.objectWrapper = new MapWrapper(this, (Map) originalObject);
        } else if (originalObject instanceof Collection) {
            // 如果是Collection型，返回CollectionWrapper
            this.objectWrapper = new CollectionWrapper(this, (Collection) originalObject);
        } else {
            // 除此以外，返回BeanWrapper
            this.objectWrapper = new BeanWrapper(this, originalObject);
        }

    }


    public static MetaObject forObject(Object object,ObjectFactory objectFactory,
                                       ObjectWrapperFactory objectWrapperFactory){
        if (object == null){
            // 处理一下null,将null包装起来
            return SystemMetaObject.NULL_META_OBJECT;
        } else {
            return new MetaObject(object,objectFactory,objectWrapperFactory);
        }
    }

    public Object getOriginalObject() {
        return originalObject;
    }

    public ObjectFactory getObjectFactory() {
        return objectFactory;
    }

    public ObjectWrapperFactory getObjectWrapperFactory() {
        return objectWrapperFactory;
    }

    /*----------------- 以下方法都是委派给 ObjectWrapper ------------------------*/


}
