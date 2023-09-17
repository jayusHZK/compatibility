package com.jayus.smallMyBatis.step05.binding;

import com.jayus.smallMyBatis.step05.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler, Serializable {
    private static final long serialVersionUID = -6424540398559729838L;

    private final Class<T> mapperInterface;

    private SqlSession sqlSession;

    private final Map<Method,MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
        this.methodCache = methodCache;
    }

    private MapperMethod cachedMapperMethod(Method method){
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null){
            mapperMethod = new MapperMethod(mapperInterface,method,sqlSession.getConfiguration());
            methodCache.put(method,mapperMethod);
        }
        return mapperMethod;
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
}
