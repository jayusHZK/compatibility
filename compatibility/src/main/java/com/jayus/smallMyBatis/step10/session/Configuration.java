package com.jayus.smallMyBatis.step10.session;

import com.jayus.smallMyBatis.step10.binding.MapperRegistry;
import com.jayus.smallMyBatis.step10.datasource.druid.DruidDataSourceFactory;
import com.jayus.smallMyBatis.step10.datasource.pooled.PooledDataSource;
import com.jayus.smallMyBatis.step10.datasource.pooled.PooledDataSourceFactory;
import com.jayus.smallMyBatis.step10.datasource.unpool.UnpooledDataSource;
import com.jayus.smallMyBatis.step10.datasource.unpool.UnpooledDataSourceFactory;
import com.jayus.smallMyBatis.step10.mapping.Environment;
import com.jayus.smallMyBatis.step10.reflection.MetaObject;
import com.jayus.smallMyBatis.step10.reflection.factory.DefaultObjectFactory;
import com.jayus.smallMyBatis.step10.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step10.reflection.wrapper.DefaultObjectWrapperFactory;
import com.jayus.smallMyBatis.step10.reflection.wrapper.ObjectWrapperFactory;
import com.jayus.smallMyBatis.step10.scripting.LanguageDriverRegistry;
import com.jayus.smallMyBatis.step10.scripting.xmltags.XMLLanguageDriver;
import com.jayus.smallMyBatis.step10.transaction.jdbc.JdbcTransaction;
import com.jayus.smallMyBatis.step10.type.TypeAliasRegistry;
import com.jayus.smallMyBatis.step10.type.TypeHandlerRegistry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 配置项
 */
public class Configuration {

    // 环境
    protected Environment environment;

    // 映射注册机
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    // 映射的语句，存在Map里
    protected final Map<String,MappedStatement> mappedStatements = new HashMap<>();

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    protected final LanguageDriverRegistry languageDriverRegistry = new LanguageDriverRegistry();

    // 类型处理器注册机
    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

    // 对象工厂和对象包装器工厂
    protected ObjectFactory objectFactory = new DefaultObjectFactory();

    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

    protected final Set<String> loadedResources = new HashSet<>();

    protected String databaseId;

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransaction.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
        languageDriverRegistry.setDefaultDriverClass(XMLLanguageDriver.class);
    }

    public void addMappers(String packageName){
        mapperRegistry.ad
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public MetaObject newMetaObject(Object object){
        return MetaObject.forObject(object,objectFactory,objectWrapperFactory);
    }

    public TypeHandlerRegistry getTypeHandlerRegistry() {
        return typeHandlerRegistry;
    }
}
