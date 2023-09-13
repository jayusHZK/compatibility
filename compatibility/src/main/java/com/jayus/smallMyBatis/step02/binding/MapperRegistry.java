package com.jayus.smallMyBatis.step02.binding;

import cn.hutool.core.lang.ClassScanner;
import com.jayus.smallMyBatis.step02.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapperRegistry {

    private final Map<Class<?>,MapperProxyFactory<?>> knowMappers = new HashMap<>();

    /**
     * 获取 mapper 映射器
     * @param type
     * @param sqlSession
     * @return
     * @param <T>
     */
    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knowMappers.get(type);
        if (mapperProxyFactory == null){
            throw new RuntimeException("Type" + type + "is not known to the MapperRegistry");
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

    /**
     * 判断 knowMappers 是否包含 type 的 mapper
     * @param type
     * @return
     * @param <T>
     */
    public <T> boolean hasMapper(Class<T> type) {
        return knowMappers.containsKey(type);
    }

    /**
     * 批量添加 mapper
     * @param packageName
     */
    public void addMappers(String packageName){
        // 扫描指定包下的所有类
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

}
