package com.jayus.smallMyBatis.step05.binding;

import cn.hutool.core.lang.ClassScanner;
import com.jayus.smallMyBatis.step05.session.Configuration;
import com.jayus.smallMyBatis.step05.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapperRegistry {

    private Configuration config;

    public MapperRegistry(Configuration config) {
        this.config = config;
    }

    private final Map<Class<?>,MapperProxyFactory<?>> knowMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knowMappers.get(type);
        if (mapperProxyFactory == null){
            throw new RuntimeException("Type" + type + " is not known to the MapperRegistry");
        } else {
            try {
                return mapperProxyFactory.newInstance(sqlSession);
            } catch (Exception e) {
                throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
            }
        }
    }

    public <T> void addMapper(Class<T> type){
        if (type.isInterface()){
            if (hasMapper(type)){
                throw new RuntimeException("Type" + type + "is already known to the MapperRegistry");
            }
            knowMappers.put(type,new MapperProxyFactory<>(type));
        }
    }

    public <T> boolean hasMapper(Class<T> type){
        return knowMappers.containsKey(type);
    }

    public void addMappers(String packageName){
        //扫描指定包下的所有类
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        //遍历mapperSet，添加mapper
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }
}
