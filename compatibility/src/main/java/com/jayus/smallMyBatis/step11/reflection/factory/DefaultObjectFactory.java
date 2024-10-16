package com.jayus.smallMyBatis.step11.reflection.factory;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * @ClassName DefaultObjectFactory
 * @Description: 默认对象工厂，所有的对象都有工厂来生成
 * @date: 2024/5/16 02:10
 */
public class DefaultObjectFactory implements ObjectFactory, Serializable {

    private static final long serialVersionUID = -8855120656740914948L;

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public <T> T create(Class<T> type) {
        return create(type,null,null);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        // 解析接口
        Class<?> classToCreate = resolveInterface(type);
        // 类实例化
        return (T) instantiateClass(classToCreate,constructorArgTypes,constructorArgs);
    }

    private <T> T instantiateClass(Class<T> type,List<Class<?>> constructorArgTypes,List<Object> constructorArgs) {
        try {
            Constructor<T> constructor;
            //如果没有传入constructor，调用空构造函数，核心是调用Constructor.newInstance
            if (constructorArgTypes == null || constructorArgs == null) {
                constructor = type.getDeclaredConstructor();
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                return constructor.newInstance();
            }
            // 如果传入constructor，调用传入的构造函数，核心是调用Constructor.newInstance
            constructor = type.getDeclaredConstructor(constructorArgTypes.toArray(new Class[constructorArgs.size()]));
            if (!constructor.isAccessible()){
                constructor.setAccessible(true);
            }
            return constructor.newInstance(constructorArgs.toArray(new Object[constructorArgs.size()]));
        } catch (Exception e) {
            // 如果出错，包装一下，重新抛出自己的异常
            StringBuilder argTypes = new StringBuilder();
            if (constructorArgTypes != null) {
                for (Class<?> argType : constructorArgTypes) {
                    argTypes.append(argType.getSimpleName());
                    argTypes.append(",");
                }
            }
            StringBuilder argValues = new StringBuilder();
            if (constructorArgs != null) {
                for (Object argValue : constructorArgs) {
                    argValues.append(argValues);
                    argValues.append(",");
                }
            }
            throw new RuntimeException("Error instantiating " + type + " with invalid types (" + argTypes + ") or values (" + argValues + "). Cause: " + e, e);
        }
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }

    protected Class<?> resolveInterface(Class<?> type) {
        Class<?> classToCreate;
        if (type == List.class || type == Collection.class || type == Iterable.class) {
            classToCreate = ArrayList.class;
        } else if (type == Map.class) {
            classToCreate = HashMap.class;
        } else if (type == SortedSet.class) {
            classToCreate = TreeSet.class;
        } else if (type == Set.class) {
            classToCreate = HashSet.class;
        } else {
            classToCreate = type;
        }
        return classToCreate;
    }
}