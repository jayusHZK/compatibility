package com.jayus.smallMyBatis.step03.binding;

import com.jayus.smallMyBatis.step03.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler, Serializable {
    private static final long serialVersionUID = -6424540398559729838L;

    private final Class<?> mapperInterface;

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession, Class<?> mapperInterface) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,args);
        } else {
            return "你被代理了" + sqlSession.selectOne(mapperInterface.getName() + "." + method.getName());
        }
    }
}
