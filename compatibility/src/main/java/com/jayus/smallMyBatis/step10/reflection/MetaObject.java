
package com.jayus.smallMyBatis.step10.reflection;

import com.jayus.smallMyBatis.step10.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step10.reflection.property.PropertyTokenizer;
import com.jayus.smallMyBatis.step10.reflection.wrapper.*;

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

    // 查找属性
    public String findProperty(String propName,boolean useCamelCaseMapping) {
        return objectWrapper.findProperty(propName,useCamelCaseMapping);
    }

    // 取得 getter 的名字列表
    public String[] getGetterNames(){
        return objectWrapper.getGetterNames();
    }

    // 取得 setter 的名字列表
    public String[] getSetterNames(){
        return objectWrapper.getSetterNames();
    }

    // 取得 setter 的类型列表
    public Class<?> getSetterType(String name){
        return objectWrapper.getSetterType(name);
    }

    // 取得 getter 的类型列表
    public Class<?> getGetterType(String name){
        return objectWrapper.getGetterType(name);
    }

    // 是否有指定的 setter
    public boolean hasSetter(String name){
        return objectWrapper.hasSetter(name);
    }

    // 是否有指定的 getter
    public boolean hasGetter(String name){
        return objectWrapper.hasGetter(name);
    }

    // 取得值
    public Object getValue(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()){
            MetaObject metaValue = metaObjectForProperty(prop.getIndexedName());
            if (metaValue == SystemMetaObject.NULL_META_OBJECT){
                // 如果是上层 那就结束 返回 null
                return null;
            } else {
                // 否则继续看下一层 递归调用 getValue
                return metaValue.getValue(prop.getChildren());
            }
        } else {
            return objectWrapper.get(prop);
        }
    }

    public void setValue(String name,Object value){
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()){
            MetaObject metaValue = metaObjectForProperty(prop.getIndexedName());
            if (metaValue == SystemMetaObject.NULL_META_OBJECT){
                if (value == null && prop.getChildren() != null){
                    // 如果上层就是 null 了，还得看有没有儿子，没有那就结束
                    return;
                } else {
                    // 否则还得 new 一个，委派给 ObjectWrapper.instantiatePropertyValue
                    metaValue = objectWrapper.instantiatePropertyValue(name,prop,objectFactory);
                }
            }
            // 递归调用 setValue
            metaValue.setValue(prop.getChildren(),value);
        } else {
            // 到了最后一层 所以委派给 ObjectWrapper.set
            objectWrapper.set(prop,value);
        }
    }

    // 为属性生成元对象
    public MetaObject metaObjectForProperty(String name) {
        Object value = getValue(name);
        return MetaObject.forObject(value,objectFactory,objectWrapperFactory);
    }

    public ObjectWrapper getObjectWrapper() {
        return objectWrapper;
    }

    // 是否是集合
    public boolean isCollection(){
        return objectWrapper.isCollection();
    }

    public void add(Object element){
        objectWrapper.add(element);
    }

    public <E> void addAll(List<E> list){
        objectWrapper.addAll(list);
    }
}
