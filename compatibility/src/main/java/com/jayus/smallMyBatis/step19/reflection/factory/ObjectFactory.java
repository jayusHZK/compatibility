package com.jayus.smallMyBatis.step19.reflection.factory;

import java.util.List;
import java.util.Properties;

/**
 * @ClassName ObjectFactory
 * @Description: 对象工厂接口
 * @date: 2024/10/17 17:19
 */
public interface ObjectFactory {

    void setProperties(Properties properties);

    <T> T create(Class<T> type);

    <T> T create(Class<T> type, List<Class<?>> constructorArgTypes,List<Object> constructorArgs);

    <T> boolean isCollection(Class<T> type);

}
