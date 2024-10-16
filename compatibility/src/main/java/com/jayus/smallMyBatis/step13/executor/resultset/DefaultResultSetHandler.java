package com.jayus.smallMyBatis.step13.executor.resultset;

import com.jayus.smallMyBatis.step13.executor.Executor;
import com.jayus.smallMyBatis.step13.executor.result.DefaultResultContext;
import com.jayus.smallMyBatis.step13.executor.result.DefaultResultHandler;
import com.jayus.smallMyBatis.step13.mapping.BoundSql;
import com.jayus.smallMyBatis.step13.mapping.MappedStatement;
import com.jayus.smallMyBatis.step13.mapping.ResultMap;
import com.jayus.smallMyBatis.step13.mapping.ResultMapping;
import com.jayus.smallMyBatis.step13.reflection.MetaClass;
import com.jayus.smallMyBatis.step13.reflection.MetaObject;
import com.jayus.smallMyBatis.step13.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step13.session.Configuration;
import com.jayus.smallMyBatis.step13.session.ResultHandler;
import com.jayus.smallMyBatis.step13.session.RowBounds;
import com.jayus.smallMyBatis.step13.type.TypeHandler;
import com.jayus.smallMyBatis.step13.type.TypeHandlerRegistry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @ClassName DefaultResultSetHandler
 * @Description: 默认Map结果处理器
 * @date: 2024/10/13 10:52
 */
public class DefaultResultSetHandler implements ResultSetHandler {

    private final Configuration configuration;

    private final MappedStatement mappedStatement;

    private final RowBounds rowBounds;

    private final ResultHandler resultHandler;

    private final BoundSql boundSql;

    private TypeHandlerRegistry typeHandlerRegistry;

    private final ObjectFactory objectFactory;

    public DefaultResultSetHandler(Executor executor, MappedStatement mappedStatement, ResultHandler resultHandler
            , RowBounds rowBounds, BoundSql boundSql) {
        this.configuration = mappedStatement.getConfiguraction();
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
            handleResultSet(rsw,resultMap,multipleResults,null);
            rsw = getNextResultSet(stmt);
            resultSetCount++;
        }
        return multipleResults.size() == 1 ? (List<Object>) multipleResults.get(0) :multipleResults;
    }

    private ResultSetWrapper getNextResultSet(Statement stmt) {
        try {
            if (stmt.getConnection().getMetaData().supportsMultipleResultSets()) {
                if (!((!stmt.getMoreResults()) && (stmt.getUpdateCount() == -1))) {
                    ResultSet rs = stmt.getResultSet();
                    return rs != null ? new ResultSetWrapper(rs,configuration) : null;
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }


    private void handleResultSet(ResultSetWrapper rsw, ResultMap resultMap, List<Object> multipleResults, ResultMapping resultMapping) throws SQLException {
        if (resultHandler == null) {
            DefaultResultHandler defaultResultHandler = new DefaultResultHandler(objectFactory);
            handleRowValuesForSimpleResultMap(rsw,resultMap,defaultResultHandler,rowBounds,null);
            multipleResults.add(defaultResultHandler.getResultList());
        }
    }

    private void handleRowValuesForSimpleResultMap(ResultSetWrapper rsw, ResultMap resultMap, ResultHandler resultHandler
            , RowBounds rowBounds, ResultMapping parentMapping) throws SQLException {
        DefaultResultContext resultContext = new DefaultResultContext();
        while (resultContext.getResultCount() < rowBounds.getLimit() && rsw.getResultSet().next()) {
            Object rowValue = getRowValue(rsw, resultMap);
            callResultHandler(resultHandler,resultContext,rowValue);
        }
    }

    private void callResultHandler(ResultHandler resultHandler,DefaultResultContext resultContext
    ,Object rowValue) {
        resultContext.nextResultObject(rowValue);
        resultHandler.handleResult(resultContext);
    }

    private Object getRowValue(ResultSetWrapper rsw, ResultMap resultMap) throws SQLException {
        Object resultObject = createResultObject(rsw, resultMap, null);
        if (resultObject != null && !typeHandlerRegistry.hasTypeHandler(resultMap.getType())) {
            final MetaObject metaObject = configuration.newMetaObject(resultObject);
            applyAutomaticMappings(rsw,resultMap,metaObject,null);
        }
        return resultObject;
    }

    private Object createResultObject(ResultSetWrapper rsw, ResultMap resultMap, String columnPrefix) throws SQLException {
        final List<Class<?>> constructorArgTypes = new ArrayList<>();
        final List<Object> constructorArgs = new ArrayList<>();
        return createResultObject(rsw, resultMap, constructorArgTypes, constructorArgs, columnPrefix);
    }

    private Object createResultObject(ResultSetWrapper rsw, ResultMap resultMap, List<Class<?>> constructorArgTypes
            , List<Object> constructorArgs, String columnPrefix) throws SQLException {
        final Class<?> resultType = resultMap.getType();
        final MetaClass metaType = MetaClass.forClass(resultType);
        if (resultType.isInterface() || metaType.hasDefaultConstructor()) {
            return objectFactory.create(resultType);
        }
        throw new RuntimeException("Do not know how to create an instance of " + resultType);
    }

    private boolean applyAutomaticMappings(ResultSetWrapper rsw, ResultMap resultMap, MetaObject metaObject
            , String columnPrefix) throws SQLException {
        final List<String> unMappedColumnNames = rsw.getUnMappedColumnNames(resultMap, columnPrefix);
        boolean foundValues = false;
        for (String columnName : unMappedColumnNames) {
            String propertyName = columnName;
            if (columnPrefix != null && !columnName.isEmpty()) {
                if (columnName.toUpperCase(Locale.ENGLISH).startsWith(columnPrefix)) {
                    propertyName = columnName.substring(columnPrefix.length());
                } else {
                    continue;
                }
            }
            final String property = metaObject.findProperty(propertyName, false);
            if (property != null && metaObject.hasSetter(property)) {
                final Class<?> propertyType = metaObject.getSetterType(property);
                if (typeHandlerRegistry.hasTypeHandler(propertyType)) {
                    final TypeHandler<?> typeHandler = rsw.getTypeHandler(propertyType, columnName);
                    final Object value = typeHandler.getResult(rsw.getResultSet(), columnName);
                    if (value != null) {
                        foundValues = true;
                    }
                    if (value != null || !propertyType.isPrimitive()) {
                        metaObject.setValue(property,value);
                    }
                }
            }
        }
        return foundValues;
    }

}
