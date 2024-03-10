package com.jayus.smallMyBatis.step09.reflection;

import com.jayus.smallMyBatis.step09.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step09.reflection.property.PropertyTokenizer;
import com.jayus.smallMyBatis.step09.reflection.wrapper.*;

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

    //对象包装工厂
    private ObjectWrapperFactory objectWrapperFactory;

    public MetaObject(Object originalObject, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory) {
        this.originalObject = originalObject;
        this.objectFactory = objectFactory;
        this.objectWrapperFactory = objectWrapperFactory;
        // 创建对象包装器
        if (originalObject instanceof ObjectWrapper) {
            // 如果对象本身是 ObjectWrapper 型 则直接赋给 objectWrapper
            this.objectWrapper = (ObjectWrapper) originalObject;
        } else if (objectWrapperFactory.hasWrapperFor(originalObject)) {
            // 如果有包装器 调用 ObjectWrapperFactory.getWrapperFor
            this.objectWrapper = objectWrapperFactory.getWrapperFor(this, originalObject);
        } else if (originalObject instanceof Map) {
            // 如果是 map 型 返回 MapWrapper
            this.objectWrapper = new MapWrapper(this, (Map<String, Object>) originalObject);
        } else if (originalObject instanceof Collection) {
            // 如果是 Collection 型 返回 CollectionWrapper
            this.objectWrapper = new CollectionWrapper(this, (Collection<Object>) originalObject);
        } else {
            // 初次之外 返回 BeanWrapper
            this.objectWrapper = new BeanWrapper(this,originalObject);
        }

    }

    /**
     * 创建元对象
     *
     * @param object
     * @param objectFactory
     * @param objectWrapperFactory
     * @return
     */
    public static MetaObject forObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory) {
        if (object == null) {
            return SystemMetaObject.NULL_META_OBJECT;
        } else {
            return new MetaObject(object, objectFactory, objectWrapperFactory);
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

    /*
     以下方法都是委派给 ObjectWrapper
     */

    public String findProperty(String propName, boolean useCamelCaseMapping) {
        return objectWrapper.findProperty(propName, useCamelCaseMapping);
    }

    /**
     * 取得 getter 的名字列表
     * @return
     */
    public String[] getGetterNames() {
        return objectWrapper.getGetterNames();
    }

    /**
     * 取得 setter 的名字列表
     * @return
     */
    public String[] getSetterNames() {
        return objectWrapper.getSetterNames();
    }

    /**
     * 取得 setter 的类型列表
     * @param name
     * @return
     */
    public Class<?> getSetterType(String name){
        return objectWrapper.getSetterType(name);
    }

    /**
     * 取得 getter 的类型列表
     * @param name
     * @return
     */
    public Class<?> getGetterType(String name){
        return objectWrapper.getGetterType(name);
    }

    /**
     * 是否有指定的 setter
     * @param name
     * @return
     */
    public boolean hasSetter(String name){
        return objectWrapper.hasSetter(name);
    }

    /**
     * 是否有指定的 getter
     * @param name
     * @return
     */
    public boolean hasGetter(String name){
        return objectWrapper.hasGetter(name);
    }

    /**
     * 取得值
     * @param name
     * @return
     */
    public Object getValue(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()){
            MetaObject metaValue = metaObjectForProperty(prop.getIndexedName());
            if (metaValue == SystemMetaObject.NULL_META_OBJECT){
                return null;
            } else {
                return metaValue.getValue(prop.getChildren());
            }
        } else {
            return objectWrapper.get(prop);
        }
    }

    /**
     * 设置值
     * @param name
     * @param value
     */
    public void setValue(String name,Object value){
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()){
            MetaObject metaValue = metaObjectForProperty(prop.getIndexedName());
            if (metaValue == SystemMetaObject.NULL_META_OBJECT){
                if (value == null && prop.getChildren() != null){
                    return ;
                }else {
                    metaValue = objectWrapper.instantiatePropertyValue(name,prop,objectFactory);
                }
            }
            metaValue.setValue(prop.getChildren(),value);
        } else {
            objectWrapper.set(prop,value);
        }

    }

    /**
     * 为属性生成元对象
     * @param name
     * @return
     */
    public MetaObject metaObjectForProperty(String name) {
        Object value = getValue(name);
        return MetaObject.forObject(value,objectFactory,objectWrapperFactory);
    }

    public ObjectWrapper getObjectWrapper() {
        return objectWrapper;
    }

    /**
     * 是否是集合
     * @return
     */
    public boolean isCollection(){
        return objectWrapper.isCollection();
    }

    /**
     * 添加属性
     * @param element
     */
    public void add(Object element){
        objectWrapper.add(element);
    }

    /**
     * 添加属性
     * @param list
     * @param <E>
     */
    public <E> void addAll(List<E> list){
        objectWrapper.addAll(list);
    }

}
