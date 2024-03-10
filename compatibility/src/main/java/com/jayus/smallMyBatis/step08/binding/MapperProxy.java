package com.jayus.smallMyBatis.step08.binding;

import com.jayus.smallMyBatis.step08.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 映射器代理类
 * @param <T>
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;

    private SqlSession sqlSession;

    private  final Class<T> mapperInterface;

    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,args);
        } else {
            final MapperMethod mapperMethod = cacheMapperMethod(method);
            return mapperMethod.execute(sqlSession,args);
        }
    }

    private MapperMethod cacheMapperMethod(Method method){
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null){
            mapperMethod = new MapperMethod(mapperInterface,method,sqlSession.getConfiguration());
            methodCache.put(method,mapperMethod);
        }
        return mapperMethod;
    }
}
