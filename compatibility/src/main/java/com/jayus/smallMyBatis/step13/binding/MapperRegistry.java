package com.jayus.smallMyBatis.step13.binding;

import cn.hutool.core.lang.ClassScanner;
import com.jayus.smallMyBatis.step13.builder.annotation.MapperAnnotationBuilder;
import com.jayus.smallMyBatis.step13.session.Configuration;
import com.jayus.smallMyBatis.step13.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName MapperRegistry
 * @Description: 映射器注册机
 * @date: 2024/10/14 21:50
 */
public class MapperRegistry {

    private Configuration config;

    public MapperRegistry(Configuration config) {
        this.config = config;
    }

    private final Map<Class<?>,MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            knownMappers.put(type,new MapperProxyFactory<>(type));
            // 解析注解类语句配置
            MapperAnnotationBuilder parser = new MapperAnnotationBuilder(config, type);
            parser.parse();
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

}

