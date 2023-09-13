package com.jayus.smallMyBatis.step04.binding;

import com.jayus.smallMyBatis.step04.session.SqlSession;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapperProxyFactory<T> {

    private final Class<?> mapperInterface;

    private Map<Method,MapperMethod> methodCache = new ConcurrentHashMap<>();

    public MapperProxyFactory(Class<?> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession){
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession,mapperInterface,methodCache);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),
                new Class[]{mapperInterface},mapperProxy);
    }
}
