package com.jayus.smallMyBatis.step12.reflection.factory;

import java.util.List;
import java.util.Properties;

/**
 * @ClassName ObjectFactory
 * @Description: 对象工厂接口
 * @date: 2024/9/19 02:08
 */
public interface ObjectFactory {

    /*
    设置属性
     */
    void setProperties(Properties properties);

    /*
    创建对象
     */
    <T> T create(Class<T> type);

    /*
    生产对象，使用指定的构造函数和构造函数参数
     */
    <T> T create(Class<T> type, List<Class<?>> constructorArgTypes,List<Object> constructorArgs);

    /*
    返回这个对象是否是集合，为了支持 Scala collections
     */
    <T> boolean isCollection(Class<T> type);
}

