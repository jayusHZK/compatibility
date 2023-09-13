package com.jayus.smallMyBatis.step01.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;

    private final Class<T> mapperInterface;

    private Map<String, String> sqlSession;

    public MapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 如果是 object 类中的方法 直接执行
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return "你被代理了" + sqlSession.get(mapperInterface.getName()+ "." + method.getName());
        }
    }
}
