package com.jayus.smallMyBatis.step08.reflection;

import com.jayus.smallMyBatis.step08.reflection.invoker.GetFieldInvoker;
import com.jayus.smallMyBatis.step08.reflection.invoker.Invoker;
import com.jayus.smallMyBatis.step08.reflection.invoker.MethodInvoker;
import com.jayus.smallMyBatis.step08.reflection.property.PropertyTokenizer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

public class MetaClass {

    // 反射器
    private Reflector reflector;

    private MetaClass(Class<?> type) {
        this.reflector = Reflector.forClass(type);
    }

    public static MetaClass forClass(Class<?> type) {
        return new MetaClass(type);
    }

    public static boolean isClassCacheEdable() {
        return Reflector.isClassCacheEnabled();
    }

    public static void setCLassCacheEnable(boolean cLassCacheEnable) {
        Reflector.setClassCacheEnabled(cLassCacheEnable);
    }

    /**
     * 获取属性的 getter 的参数类型的元素
     *
     * @param name
     * @return
     */
    public MetaClass metaClassForProperty(String name) {
        Class<?> propType = reflector.getGetterType(name);
        return MetaClass.forClass(propType);
    }

    /**
     * 获取属性的 getter 方法
     *
     * @param name
     * @return
     */
    public String findProperty(String name) {
        StringBuilder prop = buildProperty(name, new StringBuilder());
        return prop.length() > 0 ? prop.toString() : null;
    }

    /**
     * 解析属性 进行分词
     *
     * @param name
     * @param stringBuilder
     * @return
     */
    private StringBuilder buildProperty(String name, StringBuilder stringBuilder) {
        // 解析属性
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            String propertyName = reflector.findPropertyName(prop.getName());
            if (propertyName != null) {
                stringBuilder.append(propertyName);
                stringBuilder.append(".");
                MetaClass metaClass = metaClassForProperty(propertyName);
                metaClass.buildProperty(prop.getChildren(), stringBuilder);
            }
        } else {
            String propertyName = reflector.findPropertyName(name);
            if (propertyName != null) {
                stringBuilder.append(propertyName);
            }
        }
        return stringBuilder;
    }

    public String findProperty(String name,boolean useCamelCaseMapping){
        if (useCamelCaseMapping){
            name = name.replace("_","");
        }
        return findProperty(name);
    }

    public String[] getGetterNames(){
        return reflector.getGetablePropertyNames();
    }

    public String[] getSetterNames(){
        return reflector.getSetablePropertyNames();
    }

    /**
     * 获取属性的 setter 方法的参数类型
     * @param name
     * @return
     */
    public Class<?> getSetterType(String name){
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()){
            MetaClass metaProp = metaClassForProperty(prop.getName());
            return metaProp.getSetterType(prop.getChildren());
        } else {
            return reflector.getSetterType(prop.getName());
        }
    }

    /**
     * 获取属性的 getter 方法的返回类型
     * @param name
     * @return
     */
    public Class<?> getGetterType(String name){
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()){
            MetaClass metaProp = metaClassForProperty(prop.getName());
            return metaProp.getGetterType(prop.getChildren());
        } else {
            return reflector.getGetterType(prop.getName());
        }
    }

    /**
     * 根据分词器的结果获取属性的 getter 方法的返回类型的元类
     * @param prop
     * @return
     */
    private MetaClass metaClassForProperty(PropertyTokenizer prop){
        Class<?> propType = getGetterType(prop);
        return MetaClass.forClass(propType);
    }

    /**
     * 根据分词器的结果获取属性的 getter 方法的返回类型
     * @param prop
     * @return
     */
    private Class<?> getGetterType(PropertyTokenizer prop){
        Class<?> type = reflector.getGetterType(prop.getName());
        if (prop.getIndex() != null && Collection.class.isAssignableFrom(type)){
            Type returnType = getGenericGetterType(prop.getName());
            if (returnType instanceof ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType) returnType).getActualTypeArguments();
                if (actualTypeArguments != null && actualTypeArguments.length == 1){
                    returnType = actualTypeArguments[0];
                    if (returnType instanceof Class){
                        type = (Class<?>) returnType;
                    } else if (returnType instanceof ParameterizedType){
                        type = (Class<?>) returnType;
                    }
                }
            }
        }
        return type;
    }

    /**
     * 获取属性的 getter 方法的返回类型
     * @param name
     * @return
     */
    private Type getGenericGetterType(String name){
        try {
            // 获取属性的 getter 方法的 invoker
            Invoker invoker = reflector.getGetInvoker(name);
            // 如果是 MethodInvoker 获取 method 的返回类型
            if (invoker instanceof MethodInvoker){
                Field _method = MethodInvoker.class.getDeclaredField("method");
                _method.setAccessible(true);
                Method method = (Method) _method.get(invoker);
                return method.getGenericReturnType();
            } else if (invoker instanceof GetFieldInvoker){
                Field _field = GetFieldInvoker.class.getDeclaredField("field");
                _field.setAccessible(true);
                Field field = (Field) _field.get(invoker);
                return field.getGenericType();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error determining getter type for property " + name + ".  Cause: " + e, e);
        }
        return null;
    }

    /**
     * 判断是否有属性的 setter 方法
     * @param name
     * @return
     */
    public boolean hasSetter(String name){
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()){
            if (reflector.hasSetter(prop.getName())){
                MetaClass metaProp = metaClassForProperty(prop);
                return metaProp.hasSetter(prop.getChildren());
            } else {
                return false;
            }
        } else {
            return reflector.hasSetter(prop.getName());
        }
    }

    /**
     * 判断是否有属性的 getter 方法
     * @param name
     * @return
     */
    public boolean hasGetter(String name){
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()){
            // 判断是否有属性的 getter 方法
            if (reflector.hasGetter(prop.getName())){
                // 获取属性的 getter 方法的返回类型的元素
                MetaClass metaProp = metaClassForProperty(prop);
                // 递归判断分词后的属性是否有 getter 方法
                return metaProp.hasGetter(prop.getChildren());
            } else {
                return false;
            }
        } else {
            // 判断是否有属性的 getter 方法
            return reflector.hasGetter(prop.getName());
        }
    }

    public Invoker getGetInvoker(String name){
        return reflector.getGetInvoker(name);
    }

    public Invoker getSetInvoker(String name){
        return reflector.getSetInvoker(name);
    }

    public boolean hasDefaultCOnstructor(){
        return reflector.hasDefaultConstructor();
    }

}
