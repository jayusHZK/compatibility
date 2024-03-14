package com.jayus.smallMyBatis.step10.reflection.wrapper;

import com.jayus.smallMyBatis.step10.reflection.property.PropertyTokenizer;

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

    metaobj
}
