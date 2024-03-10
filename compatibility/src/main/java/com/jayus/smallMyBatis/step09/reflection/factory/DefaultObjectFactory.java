package com.jayus.smallMyBatis.step09.reflection.factory;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * 通过反射生成类实例
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
        Class<?> classToCreate = resolveInterface(type);
        return (T) instantiateClass(classToCreate,constructorArgTypes,constructorArgs);
    }

    private <T> T instantiateClass(Class<T> type,List<Class<?>> constructorArgTypes,List<Object> constructorArgs){
        try {
            Constructor<T> constructor;
            // 如果没有传入 constructor 调用空构造函数 核心是调用 Constructor.newInstance
            if (constructorArgTypes == null ||constructorArgs == null){
                constructor = type.getDeclaredConstructor();
                if (!constructor.isAccessible()){
                    constructor.setAccessible(true);
                }
                return constructor.newInstance();
            }
            // 如果传入 constructor 调用传入构造函数 核心是调用 Constructor.newInstance
            constructor = type.getDeclaredConstructor(constructorArgTypes.toArray(new Class[constructorArgTypes.size()]));
            if (!constructor.isAccessible()){
                constructor.setAccessible(true);
            }
            return constructor.newInstance(constructorArgs);
        } catch (Exception e) {
            // 如果出错 包装一下 重新抛出自己的异常
            StringBuilder argTypes = new StringBuilder();
            if (constructorArgTypes != null){
                for (Class<?> argType : constructorArgTypes) {
                    argTypes.append(argType.getSimpleName());
                    argTypes.append(",");
                }
            }
            StringBuilder argValues = new StringBuilder();
            if (constructorArgs != null){
                for (Object argValue : constructorArgs) {
                    argValues.append(argTypes);
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

    /**
     * 解析接口 将 interface 转换为实际的 class 类
     * @param type
     * @return
     */
    protected Class<?> resolveInterface(Class<?> type) {
        Class<?> classToCreate;
        if (type == List.class || type == Collection.class || type == Iterable.class) {
            classToCreate = ArrayList.class;
        } else if (type == Map.class){
            classToCreate = HashMap.class;
        } else if (type == SortedMap.class){
            classToCreate = TreeSet.class;
        } else if (type == Set.class){
            classToCreate = HashSet.class;
        } else {
            classToCreate = type;
        }
        return classToCreate;
    }
}
