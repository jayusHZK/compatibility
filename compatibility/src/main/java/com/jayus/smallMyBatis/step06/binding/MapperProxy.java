package com.jayus.smallMyBatis.step06.binding;

import com.jayus.smallMyBatis.step06.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final Class<T> mapperInterface;

    private SqlSession sqlSession;

    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession session, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = session;
        this.methodCache = methodCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,args);
        } else {
            MapperMethod mapperMethod = cachedMapperMethod(method);
            return mapperMethod.execute(sqlSession,args);
        }
    }

    /**
     * 缓存mapper方法
     *
     * @param method
     * @return
     */
    private MapperMethod cachedMapperMethod(Method method) {
        //先从缓存中获取，如果没有则创建一个
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null) {
            mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
