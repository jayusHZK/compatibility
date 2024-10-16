package com.jayus.smallMyBatis.step13.session;

import com.jayus.smallMyBatis.step13.binding.MapperRegistry;
import com.jayus.smallMyBatis.step13.datasource.druid.DruidDataSourceFactory;
import com.jayus.smallMyBatis.step13.datasource.pooled.PooledDataSourceFactory;
import com.jayus.smallMyBatis.step13.datasource.unpooled.UnpooledDataSourceFactory;
import com.jayus.smallMyBatis.step13.executor.Executor;
import com.jayus.smallMyBatis.step13.executor.SimpleExecutor;
import com.jayus.smallMyBatis.step13.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step13.executor.resultset.DefaultResultSetHandler;
import com.jayus.smallMyBatis.step13.executor.resultset.ResultSetHandler;
import com.jayus.smallMyBatis.step13.executor.statement.PreparedStatementHandler;
import com.jayus.smallMyBatis.step13.executor.statement.StatementHandler;
import com.jayus.smallMyBatis.step13.mapping.BoundSql;
import com.jayus.smallMyBatis.step13.mapping.Environment;
import com.jayus.smallMyBatis.step13.mapping.MappedStatement;
import com.jayus.smallMyBatis.step13.mapping.ResultMap;
import com.jayus.smallMyBatis.step13.reflection.MetaObject;
import com.jayus.smallMyBatis.step13.reflection.factory.DefaultObjectFactory;
import com.jayus.smallMyBatis.step13.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step13.reflection.wrapper.DefaultObjectWrapperFactoy;
import com.jayus.smallMyBatis.step13.reflection.wrapper.ObjectWrapperFactory;
import com.jayus.smallMyBatis.step13.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step13.scripting.LanguageDriverRegistry;
import com.jayus.smallMyBatis.step13.scripting.xmltags.XMLLanguageDriver;
import com.jayus.smallMyBatis.step13.transaction.Transaction;
import com.jayus.smallMyBatis.step13.transaction.jdbc.JdbcTransactionFactory;
import com.jayus.smallMyBatis.step13.type.TypeAliasRegistry;
import com.jayus.smallMyBatis.step13.type.TypeHandlerRegistry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName Configuraction
 * @Description: 配置项
 * @date: 2024/10/10 18:06
 */
public class Configuration {

    // 环境
    protected Environment environment;

    // 映射注册机
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    // 映射的语句，存在 map 里
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    // 结果映射 存在 map 里
    protected final Map<String, ResultMap> resultMaps = new HashMap<>();

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    protected final LanguageDriverRegistry languageRegistry = new LanguageDriverRegistry();

    // 类型处理器注册机
    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

    // 对象工厂和对象包装器工厂
    protected ObjectFactory objectFactory = new DefaultObjectFactory();

    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactoy();

    protected final Set<String> loadedResources = new HashSet<>();

    protected String databaseId;

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

    public MappedStatement getMappedStatment(String id) {
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

    /*
    创建结果集处理器
     */
    public ResultSetHandler newResultHandler(Executor executor, MappedStatement mappedStatement, RowBounds rowBounds, ResultHandler resultHandler
            , BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, resultHandler, rowBounds, boundSql);
    }

    /*
    生产执行器
     */
    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    /*
    创建语句执行器
     */
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter
            , RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor,mappedStatement,parameter,rowBounds,resultHandler,boundSql);
    }

    // 创建元对象
    public MetaObject newMetaObject(Object object) {
        return MetaObject.forObject(object,objectFactory,objectWrapperFactory);
    }

    // 类型处理器注册机
    public TypeHandlerRegistry getTypeHandlerRegistry() {
        return typeHandlerRegistry;
    }

    public boolean isResourceLoaded(String resource) {
        return loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource){
        loadedResources.add(resource);
    }

    public LanguageDriverRegistry getLanguageRegistry() {
        return languageRegistry;
    }

    public ParameterHandler newParameterHandler(MappedStatement mappedStatement,Object parameterObject,BoundSql boundSql) {
        // 创建参数处理器
        ParameterHandler parameterHandler = mappedStatement.getLang().createParameterHandler(mappedStatement, parameterObject, boundSql);
        // 插件的一些参数，也是在这里处理，暂时不添加这部分内容 interceptorChain.pluginAll(parameterHandler);
        return parameterHandler;
    }

    public LanguageDriver getDefaultScriptingLanguageInstance(){
        return languageRegistry.getDefaultDriver();
    }

    public ObjectFactory getObjectFactory() {
        return objectFactory;
    }

    public ResultMap getResultMap(String id) {
        return resultMaps.get(id);
    }

    public void addResultMap(ResultMap resultMap) {
        resultMaps.put(resultMap.getId(), resultMap);
    }

}
