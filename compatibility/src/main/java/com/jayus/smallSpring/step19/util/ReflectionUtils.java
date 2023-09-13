package com.jayus.smallSpring.step19.util;

import cn.hutool.core.lang.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectionUtils {

    private static final Map<Class<?>, Method[]> declareMethodsCache = new ConcurrentHashMap<>(256);

    private static final Method[] EMPTY_METHOD_ARRAY = new Method[0];

    public static Method findMethod(Class<?> clazz, String name) {
        return findMethod(clazz, name, new Class[0]);
    }

    public static Object invokeMethod(Method method, Object target) {
        return invokeMethod(method,target,new Object[0]);
    }

    public static Object invokeMethod(Method method, Object target, Object... args) {
        try {
            return method.invoke(target, args);
        } catch (Exception e) {
            handleReflectionException(e);
        }
        throw new IllegalStateException("Should never get here");
    }

    public static void handleReflectionException(Exception ex) {
        if (ex instanceof NoSuchMethodException) {
            throw new IllegalStateException("Method not found: " + ex.getMessage());
        }
        if (ex instanceof IllegalAccessException) {
            throw new IllegalStateException("Could not access method: " + ex.getMessage());
        }
        if (ex instanceof InvocationTargetException) {
            handleInvocationTargetException((InvocationTargetException) ex);
        }
        if (ex instanceof RuntimeException) {
            throw (RuntimeException) ex;
        }
        throw new UndeclaredThrowableException(ex);
    }

    // 代理异常
    public static void handleInvocationTargetException(InvocationTargetException ex) {
        rethrowRuntimeException(ex.getTargetException());
    }

    // 运行时异常
    public static void rethrowRuntimeException(Throwable ex) {
        if (ex instanceof RuntimeException) {
            throw (RuntimeException) ex;
        }
        if (ex instanceof Error) {
            throw (Error) ex;
        }
        throw new UndeclaredThrowableException(ex);
    }

    /**
     * 找到该类对应名称和参数的方法
     *
     * @param clazz
     * @param name
     * @param paramTypes
     * @return
     */
    public static Method findMethod(Class<?> clazz, String name, Class<?>... paramTypes) {
        Assert.notNull(clazz, "Class must not be null");
        Assert.notNull(name, "Method name must not be null");
        Class<?> searchType = clazz;
        while (searchType != null) {
            Method[] methods = searchType.isInterface() ? searchType.getMethods() : getDeclareMethods(clazz);
            for (Method method : methods) {
                if (name.equals(method.getName()) &&
                        (paramTypes == null || Arrays.equals(paramTypes, method.getParameterTypes()))) {
                    return method;
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }

    private static Method[] getDeclareMethods(Class<?> clazz) { // 获取类全部方法和父接口全部方法(就是该类应该有的全部方法)
        Assert.notNull(clazz, "Class must not be null");
        Method[] result = declareMethodsCache.get(clazz);
        if (result == null) {
            try {
                Method[] declaredMethods = clazz.getDeclaredMethods();
                List<Method> defaultMethods = findConcreteMethodOnInterfaces(clazz); // 获取类所有父接口方法
                if (declaredMethods == null) {
                    result = new Method[declaredMethods.length + defaultMethods.size()];
                    System.arraycopy(declaredMethods, 0, result, 0, declaredMethods.length);
                    int index = declaredMethods.length;
                    for (Method defaultMethod : defaultMethods) {
                        result[index] = defaultMethod;
                        index++;
                    }
                } else {
                    result = declaredMethods;
                }
                declareMethodsCache.put(clazz, result.length == 0 ? EMPTY_METHOD_ARRAY : result);
            } catch (Throwable ex) {
                throw new IllegalStateException("Failed to introspect Class [" + clazz.getName() +
                        "] from ClassLoader [" + clazz.getClassLoader() + "]", ex);
            }
        }
        return result;
    }

    // 获取类的父接口所有方法
    private static List<Method> findConcreteMethodOnInterfaces(Class<?> clazz) {
        List<Method> result = null;
        for (Class<?> ifc : clazz.getInterfaces()) {
            for (Method ifcMethod : ifc.getMethods()) {
                if (!Modifier.isAbstract(ifcMethod.getModifiers())) {// 是抽象方法
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(ifcMethod);
                }
            }
        }
        return result;
    }

    // 获取类的全部方法
    public static Method[] getAllDeclareMethods(Class<?> leafClass) {
        final List<Method> methods = new ArrayList<>(32);
        doWithMethods(leafClass, methods::add);
        // 将当前类的所有方法返回(父类和父接口都要)
        return methods.toArray(EMPTY_METHOD_ARRAY);
    }

    public static void doWithMethods(Class<?> clazz, MethodCallback mc) {
        doWithMethods(clazz, mc, null);
    }

    // 递归调用类被拦截的所有方法
    public static void doWithMethods(Class<?> clazz, MethodCallback mc, MethodFilter mf) {
        Method[] methods = getDeclareMethods(clazz);
        for (Method method : methods) {
            if (mf != null && !mf.matches(method)) {
                continue;
            }
            try {
                mc.doWith(method);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Not allowed to access method '" + method.getName() + "': " + e);
            }
        }
        if (clazz.getSuperclass() != null) {
            doWithMethods(clazz.getSuperclass(), mc, mf);
        } else if (clazz.isInterface()) {
            for (Class<?> superIfc : clazz.getInterfaces()) {
                doWithMethods(superIfc, mc, mf);
            }
        }
    }

    public interface MethodCallback {

        void doWith(Method method) throws IllegalArgumentException, IllegalAccessException;

    }

    public interface MethodFilter {
        boolean matches(Method method);
    }

    public static void makeAccessible(Method method) {
        if (!Modifier.isPublic(method.getModifiers()) || // 方法不是 public
                !Modifier.isPublic(method.getDeclaringClass().getModifiers()) && !method.isAccessible()) { // 类不是public并且方法是不可访问的
            method.setAccessible(true);
        }
    }

    public static boolean isEqualsMethod(Method method){
        if (method == null || !method.getName().equals("equals")){
            return false;
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        return parameterTypes.length == 1 && parameterTypes[0] == Object.class;
    }

    public static boolean isHashCodeMethod(Method method){
        return method != null && method.getName().equals("hashCode") && method.getParameterTypes().length == 0;
    }

    public static boolean isToStringMethod(Method method){
        return method != null && method.getName().equals("toString") && method.getParameterTypes().length == 0;
    }


}
