package com.jayus.smallMyBatis.step11.reflection;

import com.jayus.smallMyBatis.step11.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step11.reflection.property.PropertyTokenizer;
import com.jayus.smallMyBatis.step11.reflection.wrapper.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MetaObject
 * @Description: 元对象
 * @date: 2024/7/24 09:29
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

    public MetaObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory) {
        this.originalObject = originalObject;
        this.objectFactory = objectFactory;
        this.objectWrapperFactory = objectWrapperFactory;

        if (object instanceof ObjectWrapper){
            // 如果对象本身已经是 ObjectWrapper 型，则直接赋给 objectWrapper
            this.objectWrapper = (ObjectWrapper) object;
        } else if (objectWrapperFactory.hasWrapperFor(object)){
            // 如果有包装器，调用ObjectWrapperFactory.getWrapperFor
            this.objectWrapper = objectWrapperFactory.getWrapperFor(this,object);
        } else if (object instanceof Map){
            // 如果是 Map 型，返回 MapWrapper
            this.objectWrapper = new MapWrapper(this, (Map) object);
        } else if (object instanceof Collection) {
            // 如果是 Collection 型，返回 CollectionWrapper
            this.objectWrapper = new CollectionWrapper(this, (Collection<Object>) object);
        } else {
            // 初次以外 返回 BeanWrapper
            this.objectWrapper = new BeanWrapper(this,object);
        }
    }

    public static MetaObject forObject(Object object,ObjectFactory objectFactory,ObjectWrapperFactory objectWrapperFactory){
        if (object == null) {
            return SystemMetaObject.NULL_META_OBJECT;
        } else {
            return new MetaObject(object,objectFactory,objectWrapperFactory);
        }
    }

    public ObjectFactory getObjectFactory() {
        return objectFactory;
    }

    public ObjectWrapperFactory getObjectWrapperFactory() {
        return objectWrapperFactory;
    }

    public Object getOriginalObject() {
        return originalObject;
    }

    /*
     以下方法都是委派给 ObjectWrapper
     */

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
    public Class<?> getGetterType(String name) {
        return objectWrapper.getGetterType(name);
    }

    // 是否有指定的 setter
    public boolean hasSetter(String name) {
        return objectWrapper.hasSetter(name);
    }

    // 是否有指定的 getter
    public boolean hasGetter(String name) {
        return objectWrapper.hasGetter(name);

    }

    // 取得值
    public Object getValue(String name){
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            MetaObject metaValue = metaObjectForProperty(prop.getIndexedName());
            if (metaValue == SystemMetaObject.NULL_META_OBJECT){
                // 如果上层就是null，那就结束，返回null
                return null;
            } else {
                // 否则继续看下一层，递归调用 getValue
                return metaValue.getValue(prop.getChildren());
            }
        } else {
            return objectWrapper.get(prop);
        }
    }

    // 设置值
    public void setValue(String name,Object value){
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()){
            MetaObject metaValue = metaObjectForProperty(prop.getIndexedName());
            if (metaValue == SystemMetaObject.NULL_META_OBJECT) {
                if (value == null && prop.getChildren() != null) {
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
            // 到了最后一层，所以委派给 ObjectWrapper.set
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

    // 添加属性
    public void add(Object element) {
        objectWrapper.add(element);
    }

    // 添加属性
    public <E> void addAll(List<E> list){
        objectWrapper.addAll(list);
    }
}
