package com.jayus.smallMyBatis.step19.session;

import com.jayus.smallMyBatis.step19.binding.MapperRegistry;
import com.jayus.smallMyBatis.step19.cache.Cache;
import com.jayus.smallMyBatis.step19.cache.Impl.PerpetualCache;
import com.jayus.smallMyBatis.step19.cache.decorators.FifoCache;
import com.jayus.smallMyBatis.step19.datasource.druid.DruidDataSourceFactory;
import com.jayus.smallMyBatis.step19.datasource.pooled.PooledDataSourceFactory;
import com.jayus.smallMyBatis.step19.datasource.unpooled.UnpooledDataSourceFactory;
import com.jayus.smallMyBatis.step19.executor.CachingExecutor;
import com.jayus.smallMyBatis.step19.executor.Executor;
import com.jayus.smallMyBatis.step19.executor.SimpleExecutor;
import com.jayus.smallMyBatis.step19.executor.keygen.KeyGenerator;
import com.jayus.smallMyBatis.step19.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step19.executor.resultset.DefaultResultSetHandler;
import com.jayus.smallMyBatis.step19.executor.resultset.ResultSetHandler;
import com.jayus.smallMyBatis.step19.executor.statement.PreparedStatementHandler;
import com.jayus.smallMyBatis.step19.executor.statement.StatementHandler;
import com.jayus.smallMyBatis.step19.mapping.BoundSql;
import com.jayus.smallMyBatis.step19.mapping.Environment;
import com.jayus.smallMyBatis.step19.mapping.MappedStatement;
import com.jayus.smallMyBatis.step19.mapping.ResultMap;
import com.jayus.smallMyBatis.step19.plugin.Interceptor;
import com.jayus.smallMyBatis.step19.plugin.InterceptorChain;
import com.jayus.smallMyBatis.step19.reflection.MetaObject;
import com.jayus.smallMyBatis.step19.reflection.factory.DefaultObjectFactory;
import com.jayus.smallMyBatis.step19.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step19.reflection.wrapper.DefaultObjectWrapperFactory;
import com.jayus.smallMyBatis.step19.reflection.wrapper.ObjectWrapperFactory;
import com.jayus.smallMyBatis.step19.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step19.scripting.LanguageDriverRegistry;
import com.jayus.smallMyBatis.step19.scripting.xmltags.XMLLanguageDriver;
import com.jayus.smallMyBatis.step19.transaction.Transaction;
import com.jayus.smallMyBatis.step19.transaction.jdbc.JdbcTransactionFactory;
import com.jayus.smallMyBatis.step19.type.TypeAliasRegistry;
import com.jayus.smallMyBatis.step19.type.TypeHandlerRegistry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName Configuration
 * @Description: 配置项
 * @date: 2024/10/16 20:41
 */
public class Configuration {

    protected Environment environment;

    protected boolean useGeneratedKeys = false;

    protected boolean cacheEnabled = true;

    protected LocalCacheScope localCacheScope = LocalCacheScope.SESSION;

    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    protected final Map<String, Cache> caches = new HashMap<>();

    protected final Map<String, ResultMap> resultMaps = new HashMap<>();

    protected final Map<String, KeyGenerator> keyGenerators = new HashMap<>();

    // 插件拦截器链
    protected final InterceptorChain interceptorChain = new InterceptorChain();

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    protected final LanguageDriverRegistry languageRegistry = new LanguageDriverRegistry();

    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

    protected ObjectFactory objectFactory = new DefaultObjectFactory();

    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

    protected final Set<String> loadedResources = new HashSet<>();

    protected String databaseId;

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);

        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);

        typeAliasRegistry.registerAlias("PERPETUAL", PerpetualCache.class);
        typeAliasRegistry.registerAlias("FIFO", FifoCache.class);

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

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    /*
    创建结果集处理器
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, RowBounds rowBounds
            , ResultHandler resultHandler, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, resultHandler, rowBounds, boundSql);
    }

    /*
    生产执行器
     */
    public Executor newExecutor(Transaction transaction) {
        Executor executor = new SimpleExecutor(this, transaction);
        if (cacheEnabled) {
            executor = new CachingExecutor(executor);
        }
        return executor;
    }

    /*
    创建语句处理器
     */
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter
            , RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        // 创建语句处理器，Mybatis 这里加了路由 STATEMENT、PREPARED、CALLABLE 我们默认只根据预处理进行实例化
        StatementHandler statementHandler = new PreparedStatementHandler(executor, mappedStatement, parameter
                , rowBounds, resultHandler, boundSql);
        // 嵌入插件 代理对象 所以mybatis的拦截器只能拦截 四大对象，因为在 configuration 中只为这四个生成了代理对象
        statementHandler = (StatementHandler) interceptorChain.pluginAll(statementHandler);
        return statementHandler;
    }

    /*
    创建元对象
     */
    public MetaObject newMetaObject(Object object) {
        return MetaObject.forObject(object,objectFactory,objectWrapperFactory);
    }

    // 类型处理器注册机
    public TypeHandlerRegistry getTypeHandlerRegistry() {
        return typeHandlerRegistry;
    }

    public boolean isResourceLoader(String resource) {
        return loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource) {
        loadedResources.add(resource);
    }

    public LanguageDriverRegistry getLanguageRegistry() {
        return languageRegistry;
    }

    public ParameterHandler newParameterHandler(MappedStatement mappedStatement,Object parameterObject,BoundSql boundSql) {
        ParameterHandler parameterHandler = mappedStatement.getLang().createParameterHandler(mappedStatement, parameterObject, boundSql);
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
        resultMaps.put(resultMap.getId(),resultMap);
    }

    public void addKeyGenerator(String id,KeyGenerator keyGenerator) {
        keyGenerators.put(id,keyGenerator);
    }

    public KeyGenerator getKeyGenerator(String id) {
        return keyGenerators.get(id);
    }

    public boolean hasKeyGenerator(String id) {
        return keyGenerators.containsKey(id);
    }

    public boolean isUseGeneratedKeys(){
        return useGeneratedKeys;
    }

    public void setUseGeneratedKeys(boolean useGeneratedKeys) {
        this.useGeneratedKeys = useGeneratedKeys;
    }

    public void addInterceptor(Interceptor interceptorInstance){
        interceptorChain.addInterceptor(interceptorInstance);
    }

    public LocalCacheScope getLocalCacheScope() {
        return localCacheScope;
    }

    public void setLocalCacheScope(LocalCacheScope localCacheScope) {
        this.localCacheScope = localCacheScope;
    }

    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public void setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    public void addCache(Cache cache) {
        caches.put(cache.getId(),cache);
    }

    public Cache getCache(String id) {
        return caches.get(id);
    }

}
