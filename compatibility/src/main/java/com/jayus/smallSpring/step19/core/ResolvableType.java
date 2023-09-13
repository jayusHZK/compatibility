package com.jayus.smallSpring.step19.core;

import com.jayus.smallSpring.step19.core.util.ConcurrentReferenceHashMap;
import com.jayus.smallSpring.step19.core.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.*;

public class ResolvableType implements Serializable {

    public static final ResolvableType NONE = new ResolvableType(EmptyType.INSTANCE, null, null, 0);

    private static final ResolvableType[] EMPTY_TYPES_ARRAY = new ResolvableType[0];

    private static final ConcurrentReferenceHashMap<ResolvableType, ResolvableType> cache =
            new ConcurrentReferenceHashMap<>(256);

    private final Type type;

    private final SerializableTypeWrapper.TypeProvider typeProvider;

    private final VariableResolver variableResolver;

    private final ResolvableType componentType;

    private final Integer hash;

    private Class<?> resolved;

    private volatile ResolvableType superType;

    private volatile ResolvableType[] interfaces;

    private volatile ResolvableType[] generics;

    public ResolvableType(Type type, SerializableTypeWrapper.TypeProvider typeProvider, VariableResolver variableResolver) {
        this.type = type;
        this.typeProvider = typeProvider;
        this.variableResolver = variableResolver;
        this.componentType = null;
        this.hash = calculateHashCode();
        this.resolved = null;
    }

    public ResolvableType(Type type, SerializableTypeWrapper.TypeProvider typeProvider, VariableResolver variableResolver, Integer hash) {
        this.type = type;
        this.typeProvider = typeProvider;
        this.variableResolver = variableResolver;
        this.componentType = null;
        this.hash = hash;
        this.resolved = resolveClass();
    }

    public ResolvableType(Type type, SerializableTypeWrapper.TypeProvider typeProvider, VariableResolver variableResolver
            , ResolvableType componentType) {
        this.type = type;
        this.typeProvider = typeProvider;
        this.variableResolver = variableResolver;
        this.componentType = componentType;
        this.hash = null;
        this.resolved = resolveClass();
    }

    private ResolvableType(Class<?> clazz){
        this.resolved = (clazz != null? clazz:Object.class);
        this.type = this.resolved;
        this.typeProvider = null;
        this.variableResolver = null;
        this.componentType = null;
        this.hash = null;
    }

    public Type getType(){
        return SerializableTypeWrapper.unwrap(this.type);
    }

    public Class<?> getRawClass(){
        if (this.type == this.resolved){
            return this.resolved;
        }
        Type rawType = this.type;
        if (rawType instanceof ParameterizedType){
            rawType = ((ParameterizedType) rawType).getRawType();
        }
        return rawType instanceof Class? (Class<?>) rawType :null;
    }

    public Object getSource(){
        Object source = this.typeProvider != null ? this.typeProvider.getSource() : null;
        return source != null?source:this.type;
    }

    public Class<?> toClass(){
        return resolve(Object.class);
    }

    /*public boolean isInstance(Object obj){
        return obj != null && isa
    }

    public boolean isAssignableFrom(Class<?> other){
        return
    }*/

    public Class<?> resolve(Class<?> fallback){
        return this.resolved != null?this.resolved:fallback;
    }

    public ResolvableType getComponentType() {
        if (this == NONE) {
            return NONE;
        }
        if (this.componentType != null) {
            return this.componentType;
        }

        if (this.type instanceof Class) {
            Class<?> componentType = ((Class<?>) this.type).getComponentType();
            return forType(componentType,this.variableResolver);
        }
        if (this.type instanceof GenericArrayType){
            return forType(((GenericArrayType) this.type).getGenericComponentType(),this.variableResolver);
        }
        return resolveType().getComponentType();
    }

    private Class<?> resolveClass() {
        if (this.type == EmptyType.INSTANCE) {
            return null;
        }
        if (this.type instanceof Class) {
            return (Class<?>) this.type;
        }
        if (this.type instanceof GenericArrayType) {
            Class<?> resolveComponent = getComponentType().resolve();
            return resolveComponent != null? Array.newInstance(resolveComponent,0).getClass():null;
        }
        return resolveType().resolve();
    }

    ResolvableType resolveType(){
        if (this.type instanceof ParameterizedType){
            return forType(((ParameterizedType) this.type).getRawType(),this.variableResolver);
        }
        if (this.type instanceof WildcardType){
            Type resolved = resolveBounds(((WildcardType) this.type).getUpperBounds());
            if (resolved == null){
                resolved = resolveBounds(((WildcardType) this.type).getLowerBounds());
            }
            return forType(resolved,this.variableResolver);
        }
        if (this.type instanceof TypeVariable){
            TypeVariable<?> variable = (TypeVariable<?>) this.type;
            if (this.variableResolver != null){
                ResolvableType resolved = this.variableResolver.resolveVariable(variable);
                if (resolved != null){
                    return resolved;
                }
            }
            return forType(resolveBounds(variable.getBounds()),this.variableResolver);
        }
        return NONE;
    }

    private Type resolveBounds(Type[] bounds){
        if (bounds.length == 0 || bounds[0] == Object.class) {
            return null;
        }
        return bounds[0];
    }

    public Class<?> resolve(){
        return this.resolved;
    }

    private int calculateHashCode() {
        int hashCode = ObjectUtils.nullSafeHashCode(this.type);
        if (this.typeProvider != null) {
            hashCode = 31 * hashCode + ObjectUtils.nullSafeHashCode(this.typeProvider.getType());
        }
        if (this.variableResolver != null) {
            hashCode = 31 * hashCode + ObjectUtils.nullSafeHashCode(this.variableResolver.getSource());
        }
        if (this.componentType != null) {
            hashCode = 31 * hashCode + ObjectUtils.nullSafeHashCode(this.componentType);
        }
        return hashCode;
    }

    static ResolvableType forType(Type type, VariableResolver variableResolver) {
        return forType(type,null,variableResolver);
    }

    static ResolvableType forType(Type type, SerializableTypeWrapper.TypeProvider typeProvider
            , VariableResolver variableResolver) {
        if (type == null && typeProvider != null) {
            type = SerializableTypeWrapper.forTypeProvider(typeProvider);
        }
        if (type == null){
            return NONE;
        }
        if (type instanceof Class){
            return new ResolvableType(type,typeProvider,variableResolver,(ResolvableType)null);
        }
        cache.purgeUnreferencedEntries();
        ResolvableType resultType = new ResolvableType(type, typeProvider, variableResolver);
        ResolvableType cachedType = cache.get(resultType);
        if (cachedType == null){
            cachedType = new ResolvableType(type,typeProvider,variableResolver,resultType.hash);
            cache.put(cachedType,cachedType);
        }
        resultType.resolved = cachedType.resolved;
        return resultType;
    }

    interface VariableResolver extends Serializable {

        Object getSource();

        ResolvableType resolveVariable(TypeVariable<?> variable);
    }

    static class EmptyType implements Type, Serializable {

        static final Type INSTANCE = new EmptyType();

        Object readResolve() {
            return INSTANCE;
        }

    }


}
