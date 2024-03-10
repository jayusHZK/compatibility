package com.jayus.smallMyBatis.step08.session;

import com.jayus.smallMyBatis.step08.binding.MapperRegistry;
import com.jayus.smallMyBatis.step08.datasource.druid.DruidDataSourceFactory;
import com.jayus.smallMyBatis.step08.datasource.pooled.PooledDataSourceFactory;
import com.jayus.smallMyBatis.step08.datasource.unpooled.UnpooledDataSourceFactory;
import com.jayus.smallMyBatis.step08.executor.Executor;
import com.jayus.smallMyBatis.step08.executor.SimpleExecutor;
import com.jayus.smallMyBatis.step08.executor.resultset.DefaultResultSetHandler;
import com.jayus.smallMyBatis.step08.executor.resultset.ResultSetHandler;
import com.jayus.smallMyBatis.step08.executor.statement.PreparedStatementHandler;
import com.jayus.smallMyBatis.step08.executor.statement.StatementHandler;
import com.jayus.smallMyBatis.step08.mapping.BoundSql;
import com.jayus.smallMyBatis.step08.mapping.Environment;
import com.jayus.smallMyBatis.step08.mapping.MappedStatement;
import com.jayus.smallMyBatis.step08.reflection.MetaObject;
import com.jayus.smallMyBatis.step08.reflection.factory.DefaultObjectFactory;
import com.jayus.smallMyBatis.step08.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step08.reflection.wrapper.DefaultObjectWrapperFactory;
import com.jayus.smallMyBatis.step08.reflection.wrapper.ObjectWrapperFactory;
import com.jayus.smallMyBatis.step08.scripting.LanguageDriverRegistry;
import com.jayus.smallMyBatis.step08.scripting.xmltags.XMLLanguageDriver;
import com.jayus.smallMyBatis.step08.transaction.Transaction;
import com.jayus.smallMyBatis.step08.transaction.jdbc.JdbcTransactionFactory;
import com.jayus.smallMyBatis.step08.type.TypeAliasRegistry;
import com.jayus.smallMyBatis.step08.type.TypeHandlerRegistry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 配置项
 */
public class Configuration {

    private Environment environment;

    private MapperRegistry mapperRegistry = new MapperRegistry(this);

    // 映射的语句 存在 map 里
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    protected final LanguageDriverRegistry languageRegistry = new LanguageDriverRegistry();

    // 类型处理器注册机
    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

    // 对象工厂和对象包装器工厂
    protected ObjectFactory objectFactory = new DefaultObjectFactory();

    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

    protected final Set<String> loadedResources = new HashSet<>();

    private String databaseId;

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);

        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);

        languageRegistry.setDefaultDriverClass(XMLLanguageDriver.class);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    /**
     * 创建结果处理器
     *
     * @param executor
     * @param mappedStatement
     * @param boundSql
     * @return
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler
            , BoundSql boundSql) {
        return new PreparedStatementHandler(executor,mappedStatement,parameter,resultHandler,boundSql);
    }

    public MetaObject newMetaObject(Object object) {
        return MetaObject.forObject(object,objectFactory,objectWrapperFactory);
    }

    public TypeHandlerRegistry getTypeHandlerRegistry() {
        return typeHandlerRegistry;
    }

    public boolean isResourceLoaded(String resource){
        return loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource){
        loadedResources.add(resource);
    }

    public LanguageDriverRegistry getLanguageRegistry() {
        return languageRegistry;
    }
}
