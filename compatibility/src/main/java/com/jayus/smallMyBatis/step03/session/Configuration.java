package com.jayus.smallMyBatis.step03.session;

import com.jayus.smallMyBatis.step03.binding.MapperRegistry;
import com.jayus.smallMyBatis.step03.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * 保存相关配置项信息
 */
public class Configuration {

    /**
     * 映射器注册机
     */
    private MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 用于保存 MappedStatements
     * 这个类用于保存解析后的 SQL 语句和参数信息
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public void addMappers(String packageName){
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type){
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type,SqlSession sqlSession){
        return mapperRegistry.getMapper(type,sqlSession);
    }

    public boolean hasMapper(Class<?> type){return mapperRegistry.hasMapper(type);}

    public void addMappedStatement(MappedStatement ms){
        mappedStatements.put(ms.getId(),ms);
    }

    public MappedStatement getMappedStatement(String id){
        return mappedStatements.get(id);
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "mapperRegistry=" + mapperRegistry +
                ", mappedStatements=" + mappedStatements +
                '}';
    }
}
