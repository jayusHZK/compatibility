package com.jayus.smallMyBatis.step19.reflection.wrapper;

import com.jayus.smallMyBatis.step19.reflection.MetaObject;
import com.jayus.smallMyBatis.step19.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step19.reflection.property.PropertyTokenizer;

import java.util.List;

/**
 * @ClassName ObjectWrapper
 * @Description: 对象包装器
 * @date: 2024/10/17 18:48
 */
public interface ObjectWrapper {

    Object get(PropertyTokenizer prop);

    void set(PropertyTokenizer prop, Object value);

    String findProperty(String name,boolean useCamelCaseMapping);

    String[] getGetterNames();

    String[] getSetterNames();

    Class<?> getSetterType(String name);

    Class<?> getGetterType(String name);

    boolean hasSetter(String name);

    boolean hasGetter(String name);

    MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory);

    boolean isCollection();

    void add(Object element);

    <E> void addAll(List<E> element);

}
