package com.jayus.smallMyBatis.step11.reflection.wrapper;

import com.jayus.smallMyBatis.step11.reflection.MetaObject;
import com.jayus.smallMyBatis.step11.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step11.reflection.property.PropertyTokenizer;

import java.util.List;

/**
 * @ClassName ObjectWrapper
 * @Description: 对象包装
 * @date: 2024/5/16 02:48
 */
public interface ObjectWrapper {

    Object get(PropertyTokenizer prop);

    void set(PropertyTokenizer prop,Object value);

    // 查找属性
    String findProperty(String name,boolean useCameCaseMapping);

    // 取得 getter 的名字列表
    String[] getGetterNames();

    // 取得 setter 的名字列表
    String[] getSetterNames();

    // 取得 setter 的类型
    Class<?> getSetterType(String name);

    // 取得 getter 的类型
    Class<?> getGetterType(String name);

    // 是否有指定的 setter
    boolean hasSetter(String name);

    // 是否有指定的 getter
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
