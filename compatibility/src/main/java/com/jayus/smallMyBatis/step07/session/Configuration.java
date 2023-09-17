package com.jayus.smallMyBatis.step07.session;

import com.jayus.smallMyBatis.step07.binding.MapperRegistry;
import com.jayus.smallMyBatis.step07.datasource.druid.DruidDataSourceFactory;
import com.jayus.smallMyBatis.step07.datasource.pooled.PooledDataSourceFactory;
import com.jayus.smallMyBatis.step07.datasource.unpooled.UnpooledDataSourceFactory;
import com.jayus.smallMyBatis.step07.executor.Executor;
import com.jayus.smallMyBatis.step07.executor.SimpleExecutor;
import com.jayus.smallMyBatis.step07.executor.resultset.DefaultResultSetHandler;
import com.jayus.smallMyBatis.step07.executor.resultset.ResultSetHandler;
import com.jayus.smallMyBatis.step07.executor.statement.PreparedStatementHandler;
import com.jayus.smallMyBatis.step07.executor.statement.StatementHandler;
import com.jayus.smallMyBatis.step07.mapping.BoundSql;
import com.jayus.smallMyBatis.step07.mapping.Environment;
import com.jayus.smallMyBatis.step07.mapping.MappedStatement;
import com.jayus.smallMyBatis.step07.mapping.transaction.Transaction;
import com.jayus.smallMyBatis.step07.mapping.transaction.jdbc.JdbcTransactionFactory;
import com.jayus.smallMyBatis.step07.type.TypeAliasRegistry;

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

    private final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql){
        return new DefaultResultSetHandler(executor,mappedStatement,boundSql);
    }

    public Executor newExecutor(Transaction transaction){
        return new SimpleExecutor(this,transaction);
    }

    public StatementHandler newStatementHandler(Executor executor,MappedStatement mappedStatement,Object parameter,
                                                ResultHandler resultHandler,BoundSql boundSql){
        return new PreparedStatementHandler(executor,mappedStatement,parameter,resultHandler,boundSql);
    }

    public void addMappers(String packageName){
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type){
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type,SqlSession sqlSession){
        return mapperRegistry.getMapper(type,sqlSession);
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

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }
}
