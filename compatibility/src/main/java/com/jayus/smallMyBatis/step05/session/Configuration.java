package com.jayus.smallMyBatis.step05.session;

import com.jayus.smallMyBatis.step05.binding.MapperRegistry;
import com.jayus.smallMyBatis.step05.datasource.druid.DruidDataSourceFactory;
import com.jayus.smallMyBatis.step05.datasource.pooled.PooledDataSourceFactory;
import com.jayus.smallMyBatis.step05.datasource.unpooled.UnpooledDataSourceFactory;
import com.jayus.smallMyBatis.step05.mapping.Environment;
import com.jayus.smallMyBatis.step05.mapping.MappedStatement;
import com.jayus.smallMyBatis.step05.transaction.jdbc.JdbcTransactionFactory;
import com.jayus.smallMyBatis.step05.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    protected Environment environment;

    private MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public Configuration(){
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);

        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }

    /**
     * 用于保存解析后的 SQL 语句和参数信息
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public void addMappers(String packageName){
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type){
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type,SqlSession session){
        return mapperRegistry.getMapper(type,session);
    }

    public boolean hasMapper(Class<?> type){
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms){
        mappedStatements.put(ms.getId(),ms);
    }

    public MappedStatement getMappedStatement(String id){
        return mappedStatements.get(id);
    }

    public Environment getEnvironment(){
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }
}
