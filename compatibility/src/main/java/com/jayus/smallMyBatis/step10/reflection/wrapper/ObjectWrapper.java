package com.jayus.smallMyBatis.step10.reflection.wrapper;

import com.jayus.smallMyBatis.step10.reflection.MetaObject;
import com.jayus.smallMyBatis.step10.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step10.reflection.property.PropertyTokenizer;

import java.util.List;

/**
 * 对象包装器
 */
public interface ObjectWrapper {

    Object get(PropertyTokenizer prop);

    void set(PropertyTokenizer prop,Object value);

    String findProperty(String name,boolean useCamelCaseMaping);

    String[] getGetterNames();

    String[] getSetterNames();

    Class<?> getSetterType(String name);

    Class<?> getGetterType(String name);

    boolean hasSetter(String name);

    boolean hasGetter(String name);

    // 实例化属性
    MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory);

    // 是否是集合
    boolean isCollection();

    // 添加属性
    void add(Object element);

    // 添加属性
    <E> void addAll(List<E> element);
}
