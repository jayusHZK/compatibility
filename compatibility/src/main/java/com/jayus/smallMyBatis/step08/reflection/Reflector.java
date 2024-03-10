package com.jayus.smallMyBatis.step08.reflection;

import com.jayus.smallMyBatis.step08.reflection.invoker.GetFieldInvoker;
import com.jayus.smallMyBatis.step08.reflection.invoker.Invoker;
import com.jayus.smallMyBatis.step08.reflection.invoker.MethodInvoker;
import com.jayus.smallMyBatis.step08.reflection.invoker.SetFieldInvoker;
import com.jayus.smallMyBatis.step08.reflection.property.PropertyNamer;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 反射器 用于获取类的信息 把一个对象所含带的属性 方法以及关联的类都解析出来 满足后续对属性值的设置和获取
 */
public class Reflector {

    // 是否开启缓存
    private static boolean classCacheEnabled = true;

    // 用于保存所有属性名称的数组
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    // 线程安全的缓存 用于缓存类型和反射器
    private static final Map<Class<?>, Reflector> REFLECTOR_MAP = new ConcurrentHashMap<>();

    // 类的类型
    private Class<?> type;

    private String[] readablePropertyNames = EMPTY_STRING_ARRAY;

    private String[] writeablePropertyNames = EMPTY_STRING_ARRAY;

    // setter 方法集合 键是属性名称 值是 Invoker 对象 反射调用器
    private Map<String, Invoker> setMethods = new ConcurrentHashMap<>();

    // getter 方法集合 键是属性名称 值是 Invoker 对象 反射调用器
    private Map<String, Invoker> getMethods = new ConcurrentHashMap<>();

    // set方法参数集合 键是属性名称 值是参数类型
    private Map<String, Class<?>> setTypes = new ConcurrentHashMap<>();

    // get方法参数集合 键是属性名称 值是参数类型
    private Map<String, Class<?>> getTypes = new ConcurrentHashMap<>();

    // 默认构造方法
    private Constructor<?> defaultConstructor;

    // 属性名称到属性名称的映射 键值是属性的全大写 值就是属性名称本身
    private Map<String, String> caseInsensitivePropertyMap = new ConcurrentHashMap<>();

    public Reflector(Class<?> clazz) {
        this.type = clazz;
        // 解析类的构造函数
        addDefaultConstructor(clazz);
        // 解析类的 get 方法
        addGetMethods(clazz);
        // 解析类的 set 方法
        addSetMethods(clazz);
        // 解析类的属性字段
        addFields(clazz);
        // 解析可读属性名称 get 属性
        readablePropertyNames = getMethods.keySet().toArray(new String[getMethods.keySet().size()]);
        // 解析可读属性名称 set 属性
        writeablePropertyNames = setMethods.keySet().toArray(new String[setMethods.keySet().size()]);
        // 解析属性名称到属性名称的映射
        for (String propName : readablePropertyNames) {
            caseInsensitivePropertyMap.put(propName.toUpperCase(),propName);
        }
        for (String propName : writeablePropertyNames) {
            caseInsensitivePropertyMap.put(propName.toUpperCase(),propName);
        }
    }

    /**
     * 解析类的默认构造函数
     *
     * @param clazz
     */
    private void addDefaultConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            // 如果构造函数的参数个数是0 则认为是默认的构造函数
            if (constructor.getParameterTypes().length == 0) {
                if (canAccessPrivateMethods()) {
                    try {
                        constructor.setAccessible(true);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
                if (constructor.isAccessible()) {
                    this.defaultConstructor = constructor;
                }
            }
        }
    }

    /**
     * 判断是否可以访问类的私有方法
     *
     * @return
     */
    private boolean canAccessPrivateMethods() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            try {
                // 检查方法是否可以访问私有方法
                securityManager.checkPermission(new ReflectPermission("suppressAccessChecks"));
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * 解析类的 get 方法
     * @param clazz
     */
    private void addGetMethods(Class<?> clazz) {
        Map<String, List<Method>> conflictingGetters = new ConcurrentHashMap<>();
        Method[] methods = getClassMethods(clazz);
        for (Method method : methods) {
            String name = method.getName();
            // 如果方法以 get 开头 且方法名长度大于3
            if (name.startsWith("get") && name.length() > 3){
                if (method.getParameterTypes().length == 0){
                    // 获取属性名称
                    name = PropertyNamer.methodToProperty(name);
                    addMethodConflict(conflictingGetters,name,method);
                }
            } else if (name.startsWith("is") && name.length() >2){
                if (method.getParameterTypes().length == 0){
                    name = PropertyNamer.methodToProperty(name);
                    addMethodConflict(conflictingGetters,name,method);
                }
            }
        }
        resolveGetterConflicts(conflictingGetters);
    }

    /**
     * 获取类的所有方法
     *
     * @param clazz
     * @return
     */
    private Method[] getClassMethods(Class<?> clazz) {
        Map<String, Method> uniqueMethods = new ConcurrentHashMap<>();
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            // 保存当前类定义的方法
            addUniqueMethods(uniqueMethods, currentClass.getDeclaredMethods());
            // 看这个类的接口方法 因为当前类可能是抽象类 它的方法可能在接口中
            Class<?>[] interfaces = currentClass.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                addUniqueMethods(uniqueMethods,anInterface.getDeclaredMethods());
            }
            currentClass = currentClass.getSuperclass();
        }
        Collection<Method> methods = uniqueMethods.values();
        return methods.toArray(new Method[methods.size()]);
    }

    /**
     * 添加类的方法到 uniqueMethods 集合中
     *
     * @param uniqueMethods
     * @param declareMethods
     */
    private void addUniqueMethods(Map<String, Method> uniqueMethods, Method[] declareMethods) {
        for (Method method : declareMethods) {
            // 如果方法不是桥接方法 桥接方法是指实现了泛型接口的类或者父类中的方法
            if (!method.isBridge()) {
                // 获取方法的签名
                String signature = getSignature(method);
                // 检查是否已经存在方法该签名的方法 如果不存在 则添加到 uniqueMethods 集合中
                if (!uniqueMethods.containsKey(signature)) {
                    if (canAccessPrivateMethods()) {
                        try {
                            method.setAccessible(true);
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        }
                    }
                    uniqueMethods.put(signature, method);
                }
            }
        }
    }

    /**
     * 获取方法签名 签名由返回值类型 + 方法名 + 参数类型组成
     *
     * @param method
     * @return
     */
    private String getSignature(Method method) {
        StringBuilder sb = new StringBuilder();
        // 返回值类型
        Class<?> returnType = method.getReturnType();
        if (returnType != null) {
            sb.append(returnType.getName()).append("#");
        }
        // 方法名
        sb.append(method.getName());
        // 参数类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            if (i == 0) {
                sb.append(":");
            } else {
                sb.append(",");
            }
            sb.append(parameterTypes[i].getName());
        }
        return sb.toString();
    }

    /**
     * 根据属性名添加对应的 get 方法到 conflictingGetters 集合中
     * @param conflictingGetters
     * @param name
     * @param method
     */
    private void addMethodConflict(Map<String,List<Method>> conflictingGetters,String name,Method method){
        List<Method> list = conflictingGetters.computeIfAbsent(name, k -> new ArrayList<>());
        list.add(method);
    }

    /**
     * 解决 get 方法冲突
     * @param conflictingGetters
     */
    private void resolveGetterConflicts(Map<String,List<Method>> conflictingGetters){
        for (String propName : conflictingGetters.keySet()) {
            // 获取该属性的所有 get 方法
            List<Method> methods = conflictingGetters.get(propName);
            // 获取集合的迭代器 先拿到第一个元素
            Iterator<Method> iterator = methods.iterator();
            Method firstMethod = iterator.next();
            if (methods.size() == 1){
                // 如果只有一个 get 方法 则添加到 getMethods 集合中
                addGetMethod(propName,firstMethod);
            } else {
                // 如果不止一个 get 方法 则需要判断方法返回值类型是否相同
                Method getter = firstMethod;
                Class<?> getterType = firstMethod.getReturnType();
                while (iterator.hasNext()){
                    Method method = iterator.next();
                    Class<?> methodType = method.getReturnType();
                    // 如果返回值不同 则抛出异常
                    if (!methodType.equals(getterType)){
                        throw new RuntimeException("Illegal overloaded getter method with ambiguous type for property " + propName + " in class " + firstMethod.getDeclaringClass() + ".  This breaks the JavaBeans " + "specification and can cause unpredicatble results.");
                    } else if (methodType.isAssignableFrom(getterType)){
                        // 如果返回值类型相同 且是 getterType 的父类 则什么都不做
                    } else if(getterType.isAssignableFrom(methodType)){
                        // 如果返回值类型相同 且是 getterType 的子类 则使用当前方法
                        getter = method;
                        getterType = methodType;
                    } else {
                        throw new RuntimeException("Illegal overloaded getter method with ambiguous type for property " + propName + " in class " + firstMethod.getDeclaringClass() + ".  This breaks the JavaBeans " + "specification and can cause unpredicatble results.");
                    }
                }
                addGetMethod(propName,getter);
            }
        }
    }

    /**
     * 添加 get 方法到 getMethods 集合中
     * @param propName
     * @param method
     */
    private void addGetMethod(String propName,Method method){
        if (isValidPropertyName(propName)){
            // 将属性名和 get 方法添加到getMethods 集合中
            getMethods.put(propName,new MethodInvoker(method));
            // 把属性名和返回值类型添加到 getTypes 集合中
            getTypes.put(propName,method.getReturnType());
        }
    }

    /**
     * 判断属性名是否合法
     * @param propName
     * @return
     */
    private boolean isValidPropertyName(String propName){
        return !propName.startsWith("$") && !"setialVersionUID".equals(propName) ||"class".equals(propName);
    }

    /**
     * 解析类的 set 方法
     * @param clazz
     */
    private void addSetMethods(Class<?> clazz){
        Map<String,List<Method>> conflictingSetters = new HashMap<>();
        // 获取类中定义的所有方法
        Method[] methods = getClassMethods(clazz);
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("get") && name.length() > 3){
                if (method.getParameterTypes().length == 1){
                    name = PropertyNamer.methodToProperty(name);
                    addMethodConflict(conflictingSetters,name,method);;
                }
            }
        }
        resolveGetterConflicts(conflictingSetters);
    }

    /**
     * 解析类的属性字段
     * @param clazz
     */
    private void addFields(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (canAccessPrivateMethods()){
                try {
                    field.setAccessible(true);
                } catch (SecurityException e) {
                }
            }
            if (field.isAccessible()){
                if (!setMethods.containsKey(field.getName())){
                    int modifiers = field.getModifiers();
                    // 如果该属性不是 final 也不是 static 类型 则添加到 setMethods 集合中
                    if (!(Modifier.isFinal(modifiers) && Modifier.isStatic(modifiers))){
                        addSetField(field);
                    }
                }
                if (!getMethods.containsKey(field.getName())){
                    addGetField(field);
                }
            }
        }
        // 递归解析父类的属性字段
        if (clazz.getSuperclass() != null){
            addFields(clazz.getSuperclass());
        }
    }

    /**
     * 添加属性字段到 getMethods 集合中
     * @param field
     */
    private void addGetField(Field field){
        if (isValidPropertyName(field.getName())){
            getMethods.put(field.getName(),new GetFieldInvoker(field));
            getTypes.put(field.getName(),field.getType());
        }
    }

    /**
     * 添加属性字段到 setMethods 集合中
     * @param field
     */
    private void addSetField(Field field){
        if (isValidPropertyName(field.getName())){
            setMethods.put(field.getName(),new SetFieldInvoker(field));
            setTypes.put(field.getName(),field.getType());
        }
    }

    public Class<?> getType() {
        return type;
    }

    /**
     * 获取默认构造
     * @return
     */
    public Constructor<?> getDefaultConstructor() {
        if (defaultConstructor != null)
            return defaultConstructor;
        throw new RuntimeException("There is no default constructor for " + type);
    }

    public boolean hasDefaultConstructor(){
        return defaultConstructor != null;
    }

    /**
     * 根据参数名称获取对应的 set 方法参数类型
     * @param propertyName
     * @return
     */
    public Class<?> getSetterType(String propertyName){
        Class<?> clazz = setTypes.get(propertyName);
        if (clazz == null){
            throw new RuntimeException("There is no setter for property named '" + propertyName + "' in '" + type + "'");
        }
        return clazz;
    }

    /**
     * 根据参数名称获取对应的 set 反射调用器
     * @param propertyName
     * @return
     */
    public Invoker getSetInvoker(String propertyName){
        Invoker method = setMethods.get(propertyName);
        if (method == null){
            throw new RuntimeException("There is no setter for property named '" + propertyName + "' in '" + type + "'");
        }
        return method;
    }

    /**
     * 根据参数名称获取对应的 get 反射调用器
     * @param propertyName
     * @return
     */
    public Invoker getGetInvoker(String propertyName){
        Invoker method = getMethods.get(propertyName);
        if (method == null){
            throw new RuntimeException("There is no getter for property named '" + propertyName + "' in '" + type + "'");
        }
        return method;
    }

    /**
     * 根据参数名称获取对应的 get 方法参数类型
     * @param propertyName
     * @return
     */
    public Class<?> getGetterType(String propertyName){
        Class<?> clazz = getTypes.get(propertyName);
        if (clazz == null){
            throw new RuntimeException("There is no getter for property named '" + propertyName + "' in '" + type + "'");
        }
        return clazz;
    }

    public String[] getGetablePropertyNames() {
        return readablePropertyNames;
    }

    public String[] getSetablePropertyNames() {
        return writeablePropertyNames;
    }

    /**
     * 判断属性名称是否有 set 方法
     * @param propertyName
     * @return
     */
    public boolean hasSetter(String propertyName){
        return setMethods.keySet().contains(propertyName);
    }

    /**
     * 判断属性名称是否有 get 方法
     * @param propertyName
     * @return
     */
    public boolean hasGetter(String propertyName){
        return setMethods.keySet().contains(propertyName);
    }

    /**
     * 根据参数名称获取对应的参数名称
     * @param name
     * @return
     */
    public String findPropertyName(String name){
        return caseInsensitivePropertyMap.get(name.toUpperCase(Locale.ENGLISH));
    }

    public static Reflector forClass(Class<?> clazz){
        // 如果开启了缓存 则从缓存中获取
        if (classCacheEnabled){
            Reflector cached = REFLECTOR_MAP.get(clazz);
            if (cached == null){
                cached = new Reflector(clazz);
                REFLECTOR_MAP.put(clazz,cached);
            }
            return cached;
        } else {
            // 如果没有开启缓存 则直接创建 Reflector 对象
            return new Reflector(clazz);
        }
    }

    public static void setClassCacheEnabled(boolean classCacheEnabled) {
        Reflector.classCacheEnabled = classCacheEnabled;
    }

    public static boolean isClassCacheEnabled() {
        return classCacheEnabled;
    }
}
