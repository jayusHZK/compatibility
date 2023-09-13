package com.jayus.smallSpring.step19.util;

import cn.hutool.core.lang.Assert;

import java.io.Closeable;
import java.io.Externalizable;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class ClassUtils {

    private static final Set<Class<?>> javaLangurgeInterfaces;

    private static final Map<String, Class<?>> commonCLassCache = new HashMap<>(64);

    // 基础数据类型
    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new IdentityHashMap<>(8);

    // 技术数据类型包装类
    private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new IdentityHashMap<>(8);

    static {
        Class<?>[] javaLanguageInterfaceArray = {Serializable.class, Externalizable.class,
                Closeable.class, AutoCloseable.class, Cloneable.class, Comparable.class};
        registerCommonClass(javaLanguageInterfaceArray);
        javaLangurgeInterfaces = new HashSet<>(Arrays.asList(javaLanguageInterfaceArray));
    }

    private static void registerCommonClass(Class<?>... commonClasses) {
        for (Class<?> clazz : commonClasses) {
            commonCLassCache.put(clazz.getName(), clazz);
        }
    }

    public static ClassLoader getDefaultCLassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }
        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    public static boolean isCglibProxyClass(Class<?> clazz) {
        return clazz != null && isCglibProxyClassName(clazz.getName());
    }

    public static boolean isCglibProxyClassName(String className) {
        return className != null && className.contains("$$");
    }

    public static String getQualifiedMethodName(Method method, Class<?> clazz) {
        Assert.notNull(method, "Method must not be null");
        return (clazz != null ? clazz : method.getDeclaringClass()).getName() + "." + method.getName();
    }

    public static Class<?>[] getAllInterfacesForClass(Class<?> clazz){
        return getAllInterfacesForCLass(clazz,null);
    }

    public static Class<?>[] getAllInterfacesForCLass(Class<?> clazz,ClassLoader classLoader){
        return toClassArray(getAllInterfacesForClassAsSet(clazz,classLoader));
    }

    public static Set<Class<?>> getAllInterfacesForClassAsSet(Class<?> clazz,ClassLoader classLoader){
        Assert.notNull(clazz, "Class must not be null");
        if (clazz.isInterface() && isVisible(clazz,classLoader)){
            return Collections.singleton(clazz);
        }
        Set<Class<?>> interfaces = new LinkedHashSet<>();
        Class<?> current = clazz;
        while (current != null){
            Class<?>[] ifcs = current.getInterfaces();
            for (Class<?> ifc : ifcs) {
                if (isVisible(ifc,classLoader)){
                    interfaces.add(ifc);
                }
            }
            current = current.getSuperclass();
        }
        return interfaces;
    }

    /**
     *  是不是指定类加载器加载的类
     * @param clazz
     * @param classLoader
     * @return
     */
    public static boolean isVisible(Class<?> clazz,ClassLoader classLoader){
        if (classLoader == null){
            return true;
        }
        if (clazz.getClassLoader() == classLoader){
            return true;
        }

        return isLoadable(clazz,classLoader);
    }

    /**
     * 是不是指定类加载器加载的类
     * @param clazz
     * @param classLoader
     * @return
     */
    private static boolean isLoadable(Class<?> clazz ,ClassLoader classLoader){
        try {
            return clazz == classLoader.loadClass(clazz.getName());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static Class<?>[] toClassArray(Collection<Class<?>> collection){
        return collection.toArray(new Class<?>[0]);
    }

    public static boolean isJavaLanguageInterface(Class<?> ifc){
        return javaLangurgeInterfaces.contains(ifc);
    }

    public static boolean isInnerClass(Class<?> clazz){
        return clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers());
    }

    public static boolean isAssignable(Class<?> lhsType,Class<?> rhsType){
        Assert.notNull(lhsType, "Left-hand side type must not be null");
        Assert.notNull(rhsType, "Right-hand side type must not be null");
        if (lhsType.isAssignableFrom(rhsType)){
            return true;
        }
        if (lhsType.isPrimitive()){ // 是否是基础数据类型
            Class<?> resolvedPrimitive = primitiveWrapperTypeMap.get(rhsType);
            if (lhsType == resolvedPrimitive){
                return true;
            }
        } else {
            Class<?> resolvedWrapper = primitiveTypeToWrapperMap.get(rhsType);
            if (resolvedWrapper != null && lhsType.isAssignableFrom(resolvedWrapper)){
                return true;
            }
        }
        return false;
    }

}
