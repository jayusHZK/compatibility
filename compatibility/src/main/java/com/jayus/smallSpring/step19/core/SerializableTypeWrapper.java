package com.jayus.smallSpring.step19.core;

import com.jayus.smallSpring.step19.core.util.ObjectUtils;
import com.jayus.smallSpring.step19.util.ReflectionUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.*;
import java.util.concurrent.ConcurrentHashMap;

public final class SerializableTypeWrapper {

    private static final Class<?>[] SUPPORTED_SERIALIZABLE_TYPES = {GenericArrayType.class,
            ParameterizedType.class, TypeVariable.class, WildcardType.class};


    static final ConcurrentHashMap<Type, Type> cache = new ConcurrentHashMap<>(256);

    static Type forTypeProvider(TypeProvider provider) {
        Type providedType = provider.getType();
        if (providedType == null || providedType instanceof Serializable) {
            return providedType;
        }
        if (GraalDetector.isImageCode() || !Serializable.class.isAssignableFrom(Class.class)) {
            return providedType;
        }
        Type cached = cache.get(providedType);
        if (cached != null) return cached;
        for (Class<?> type : SUPPORTED_SERIALIZABLE_TYPES) {
            if (type.isInstance(providedType)) {
                ClassLoader classLoader = provider.getClass().getClassLoader();
                Class<?>[] interfaces = new Class<?>[]{type, SerializableTypeWrapper.class, Serializable.class};
                TypeProxyInvocationHandler handler = new TypeProxyInvocationHandler(provider);
                cached = (Type) Proxy.newProxyInstance(classLoader,interfaces,handler);
                cache.put(providedType,cached);
                return cached;
            }
        }
        throw new IllegalArgumentException("Unsupported Type class: " + providedType.getClass().getName());
    }

    public static <T extends Type> T unwrap(T type) {
        Type unwrapped = type;
        while (unwrapped instanceof SerialzableTypeProxy) {
            unwrapped = ((SerialzableTypeProxy) type).getTypeProvider().getType();
        }
        return unwrapped != null ? (T) unwrapped : null;
    }


    interface TypeProvider extends Serializable {
        Type getType();

        default Object getSource() {
            return null;
        }
    }

    interface SerialzableTypeProxy {

        TypeProvider getTypeProvider();

    }

    private static class TypeProxyInvocationHandler implements InvocationHandler, Serializable {

        private final TypeProvider provider;

        public TypeProxyInvocationHandler(TypeProvider provider) {
            this.provider = provider;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("equlas") && args != null) {
                Object other = args[0];
                if (other instanceof Type) {
                    other = unwrap((Type) other);
                }
                return ObjectUtils.nullSafeEquals(this.provider.getType(), other);
            } else if (method.getName().equals("hashCode")) {
                return ObjectUtils.nullSafeHashCode(this.provider.getType());
            } else if (method.getName().equals("getTypeProvider")){
                return this.provider;
            }
            if (Type.class == method.getReturnType() && args == null){
                return forTypeProvider(new MethodInvokeTypeProvider(this.provider,method,-1));
            } else if (Type.class == method.getReturnType() && args == null){
                Type[] result = new Type[((Type[]) method.invoke(this.provider.getType())).length];
                for (int i = 0; i < result.length; i++) {
                    result[i] = forTypeProvider(new MethodInvokeTypeProvider(this.provider,method,i));
                }
                return result;
            }
            try {
                return method.invoke(this.provider.getType(),args);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class MethodInvokeTypeProvider implements TypeProvider {

        private final TypeProvider provider;

        private final String methodName;

        private final Class<?> declaringClass;

        private final int index;

        private transient Method method;

        private transient volatile Object result;

        public MethodInvokeTypeProvider(TypeProvider provider, Method method, int index) {
            this.provider = provider;
            this.methodName = method.getName();
            this.declaringClass = method.getDeclaringClass();
            this.index = index;
            this.method = method;
        }

        @Override
        public Type getType() {
            Object result = this.result;
            if (result == null){
                result = ReflectionUtils.invokeMethod(this.method,this.provider.getType());
                this.result = result;
            }
            return result instanceof Type[]?((Type[])result)[this.index] : (Type) result;
        }

        @Override
        public Object getSource() {
            return null;
        }

        private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
            inputStream.defaultReadObject();
            ReflectionUtils.findMethod(this.declaringClass,this.methodName);
            if (method == null){
                throw new IllegalStateException("Cannot find method on deserialization: " + this.methodName);
            }
            if (method.getReturnType() != Type.class && method.getReturnType() != Type[].class){
                throw new IllegalStateException(
                        "Invalid return type on deserialized method - needs to be Type or Type[]: " + method);
            }
            this.method = method;
        }
    }



}
