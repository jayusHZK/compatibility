package com.jayus.smallMyBatis.step13.reflection.wrapper;

import com.jayus.smallMyBatis.step13.reflection.MetaObject;
import com.jayus.smallMyBatis.step13.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step13.reflection.property.PropertyTokenizer;

import java.util.List;

/**
 * @ClassName ObjectWrapper
 * @Description: 对象包装器
 * @date: 2024/10/12 09:24
 */
public interface ObjectWrapper {

    // get
    Object get(PropertyTokenizer prop);

    // set
    void set(PropertyTokenizer prop,Object value);

    // 查找属性
    String findProperty(String name,boolean useCamelCaseMapping);

    // 取得getter的名字列表
    String[] getGetterNames();

    // 取得setter的名字列表
    String[] getSetterNames();

    // 是否有指定的setter
    Class<?> getSetterType(String name);

    // 是否有指定的getter
    Class<?> getGetterType(String name);

    // 是否有指定的setter
    boolean hasSetter(String name);

    // 是否有指定的getter
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
