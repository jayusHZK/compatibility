package com.jayus.smallMyBatis.step12.executor.resultset;

import com.jayus.smallMyBatis.step12.executor.Executor;
import com.jayus.smallMyBatis.step12.executor.result.DefaultResultContext;
import com.jayus.smallMyBatis.step12.executor.result.DefaultResultHandler;
import com.jayus.smallMyBatis.step12.mapping.BoundSql;
import com.jayus.smallMyBatis.step12.mapping.MappedStatement;
import com.jayus.smallMyBatis.step12.mapping.ResultMap;
import com.jayus.smallMyBatis.step12.mapping.ResultMapping;
import com.jayus.smallMyBatis.step12.reflection.MetaClass;
import com.jayus.smallMyBatis.step12.reflection.MetaObject;
import com.jayus.smallMyBatis.step12.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step12.session.Configuration;
import com.jayus.smallMyBatis.step12.session.ResultHandler;
import com.jayus.smallMyBatis.step12.session.RowBounds;
import com.jayus.smallMyBatis.step12.type.TypeHandler;
import com.jayus.smallMyBatis.step12.type.TypeHandlerRegistry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @ClassName DefaultResultSetHandler
 * @Description: 默认 Map 结果处理器
 * @date: 2024/9/25 10:47
 */
public class DefaultResultSetHandler implements ResultSetHandler {

    private final Configuration configuration;

    private final MappedStatement mappedStatement;

    private final RowBounds rowBounds;

    private final ResultHandler resultHandler;

    private final BoundSql boundSql;

    private final TypeHandlerRegistry typeHandlerRegistry;

    private final ObjectFactory objectFactory;

    public DefaultResultSetHandler(Executor executor, MappedStatement mappedStatement, ResultHandler resultHandler, RowBounds rowBounds, BoundSql boundSql) {
        this.configuration = mappedStatement.getConfiguration();
        this.rowBounds = rowBounds;
        this.boundSql = boundSql;
        this.mappedStatement = mappedStatement;
        this.resultHandler = resultHandler;
        this.objectFactory = configuration.getObjectFactory();
        this.typeHandlerRegistry = configuration.getTypeHandlerRegistry();
    }

    @Override
    public List<Object> handleResultSets(Statement stmt) throws SQLException {
        final List<Object> multipleResults = new ArrayList<>();
        int resultSetCount = 0;
        ResultSetWrapper rsw = new ResultSetWrapper(stmt.getResultSet(), configuration);
        List<ResultMap> resultMaps = mappedStatement.getResultMaps();
        while (rsw != null && resultMaps.size() > resultSetCount) {
            ResultMap resultMap = resultMaps.get(resultSetCount);
            handleResultSet(rsw, resultMap, multipleResults, null);
            rsw = getNextResultSet(stmt);
            resultSetCount++;
        }
        return multipleResults.size() == 1 ? (List<Object>) multipleResults.get(0) : multipleResults;
    }

    private ResultSetWrapper getNextResultSet(Statement stmt) {
        try {
            if (stmt.getConnection().getMetaData().supportsMultipleResultSets()) {
                if (!((!stmt.getMoreResults()) && (stmt.getUpdateCount() == -1))) {
                    ResultSet rs = stmt.getResultSet();
                    return rs != null ? new ResultSetWrapper(rs, configuration) : null;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    private void handleResultSet(ResultSetWrapper rsw, ResultMap resultMap, List<Object> multipleResults, ResultMapping resultMapping) throws SQLException {
        if (resultHandler == null) {
            // 1. 新创建结果处理器
            DefaultResultHandler defaultResultHandler = new DefaultResultHandler(objectFactory);
            // 2. 封装数据
            handleRowValueForSimpleResultMap(rsw, resultMap, defaultResultHandler, rowBounds, null);
            // 3 保存结果
            multipleResults.add(defaultResultHandler.getResultList());
        }
    }

    private void handleRowValueForSimpleResultMap(ResultSetWrapper rsw, ResultMap resultMap, ResultHandler resultHandler, RowBounds rowBounds
            , ResultMapping resultMapping) throws SQLException {
        DefaultResultContext resultContext = new DefaultResultContext();
        while (resultContext.getResultCount() < rowBounds.getLimit() && rsw.getResultSet().next()) {
            Object rowValue = getRowValue(rsw, resultMap);
            callResultHandler(resultHandler, resultContext, rowValue);
        }
    }

    private void callResultHandler(ResultHandler resultHandler, DefaultResultContext resultContext, Object rowObject) {
        resultContext.nextResultObject(rowObject);
        resultHandler.handlerResult(resultContext);
    }


    private Object getRowValue(ResultSetWrapper rsw, ResultMap resultMap) throws SQLException {
        // 根据返回类型，实例化对象
        Object resultObject = createResultObject(rsw, resultMap, null);
        if (resultObject != null && !typeHandlerRegistry.hasTypeHandler(resultMap.getType())) {
            final MetaObject metaObject = configuration.newMetaObject(resultObject);
            applyAutomaticMappings(rsw, resultMap, metaObject, null);
        }
        return resultObject;
    }

    private Object createResultObject(ResultSetWrapper rsw, ResultMap resultMap, String columnPrefix) {
        final List<Class<?>> constructorArgTypes = new ArrayList<>();
        final List<Object> constructorArgs = new ArrayList<>();
        return createResultObject(rsw, resultMap, constructorArgTypes, constructorArgs, columnPrefix);
    }

    /*
    创建结果
     */
    private Object createResultObject(ResultSetWrapper rsw, ResultMap resultMap, List<Class<?>> constructorArgTypes
            , List<Object> constructorArgs, String columnPrefix) {
        final Class<?> resultType = resultMap.getType();
        final MetaClass metaType = MetaClass.forClass(resultType);
        if (resultType.isInterface() || metaType.hasDefaultConstructor()) {
            // 普通的Bean对象类型
            return objectFactory.create(resultType);
        }
        throw new RuntimeException("Do not know how to create an instance of " + resultType);
    }

    private boolean applyAutomaticMappings(ResultSetWrapper rsw, ResultMap resultMap, MetaObject metaObject, String columnPrefix) throws SQLException {
        List<String> unmappedColumnNames = rsw.getUnmappedColumnNames(resultMap, columnPrefix);
        boolean foundValues = false;
        for (String columnName : unmappedColumnNames) {
            String propertyName = columnName;
            if (columnPrefix != null && !columnName.isEmpty()) {
                if (columnName.toUpperCase(Locale.ENGLISH).startsWith(columnPrefix)) {
                    propertyName = columnName.substring(columnPrefix.length());
                } else {
                    continue;
                }
            }
            final String property = metaObject.findProeprty(propertyName, false);
            if (property != null && metaObject.hasSetter(property)) {
                final Class<?> propertyType = metaObject.getSetterType(property);
                if (typeHandlerRegistry.hasTypeHandler(propertyType)) {
                    final TypeHandler<?> typeHandler = rsw.getTypeHandler(propertyType, columnName);
                    // 使用 TypeHander 取得结果
                    final Object value = typeHandler.getResult(rsw.getResultSet(), columnName);
                    if (value != null) {
                        foundValues = true;
                    }
                    if (value != null || !propertyType.isPrimitive()) {
                        // 通过反射工具类设置属性值
                        metaObject.setValue(property, value);
                    }
                }
            }
        }
        return foundValues;
    }

}
