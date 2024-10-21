package com.jayus.smallMyBatis.step19.executor.resultset;

import com.jayus.smallMyBatis.step19.executor.Executor;
import com.jayus.smallMyBatis.step19.executor.result.DefaultResultContext;
import com.jayus.smallMyBatis.step19.executor.result.DefaultResultHandler;
import com.jayus.smallMyBatis.step19.mapping.BoundSql;
import com.jayus.smallMyBatis.step19.mapping.MappedStatement;
import com.jayus.smallMyBatis.step19.mapping.ResultMap;
import com.jayus.smallMyBatis.step19.mapping.ResultMapping;
import com.jayus.smallMyBatis.step19.reflection.MetaClass;
import com.jayus.smallMyBatis.step19.reflection.MetaObject;
import com.jayus.smallMyBatis.step19.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step19.session.Configuration;
import com.jayus.smallMyBatis.step19.session.ResultHandler;
import com.jayus.smallMyBatis.step19.session.RowBounds;
import com.jayus.smallMyBatis.step19.type.TypeHandler;
import com.jayus.smallMyBatis.step19.type.TypeHandlerRegistry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @ClassName DefaultResultSetHandler
 * @Description: 默认 Map 结果处理器
 * @date: 2024/10/18 20:08
 */
public class DefaultResultSetHandler implements ResultSetHandler {

    private static final Object NO_VALUE = new Object();

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
    public  List<Object> handleResultSets(Statement stmt) throws SQLException {
        final List<Object> multipleResults = new ArrayList<>();

        int resultSetCount = 0;
        ResultSetWrapper rsw = new ResultSetWrapper(stmt.getResultSet(),configuration);
        List<ResultMap> resultMaps = mappedStatement.getResultMaps();
        while (rsw != null && resultMaps.size() > resultSetCount) {
            ResultMap resultMap = resultMaps.get(resultSetCount);
            handleResultSet(rsw,resultMap,multipleResults,null);
            rsw = getNextResultSet(stmt);
            resultSetCount++;
        }
        return multipleResults.size() == 1 ? (List<Object>) multipleResults.get(0) : multipleResults;
    }

    private ResultSetWrapper getNextResultSet(Statement stmt) {
        try {
            if (stmt.getConnection().getMetaData().supportsMultipleResultSets()){
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
            // 新创建结果处理器
            DefaultResultHandler defaultResultHandler = new DefaultResultHandler(objectFactory);
            // 封装数据
            handleRowValuesForSimpleResultMap(rsw,resultMap,defaultResultHandler,rowBounds,null);
            // 保存结果
            multipleResults.add(defaultResultHandler.getResultList());
        }
    }

    private void handleRowValuesForSimpleResultMap(ResultSetWrapper rsw,ResultMap resultMap,ResultHandler resultHandler,RowBounds rowBounds,ResultMapping resultMapping) throws SQLException {
        DefaultResultContext resultContext = new DefaultResultContext();
        ResultSet resultSet = rsw.getResultSet();
        while (resultContext.getResultCount() < rowBounds.getLimit() && resultSet.next()) {
            Object rowValue = getRowValue(rsw, resultMap);
            callResultHandler(resultHandler,resultContext,rowValue);
        }
    }

    private void callResultHandler(ResultHandler resultHandler,DefaultResultContext resultContext,Object rowValue) {
        resultContext.nextResultObject(rowValue);
        resultHandler.handleResult(resultContext);
    }

    private Object getRowValue(ResultSetWrapper rsw,ResultMap resultMap) throws SQLException {
        Object resultObject = createResultObject(rsw, resultMap, null);
        if (resultObject != null || !typeHandlerRegistry.hasTypeHandler(resultMap.getType())) {
            final MetaObject metaObject = configuration.newMetaObject(resultObject);
            applyAutomaticMappings(rsw,resultMap,metaObject,null);
            applyPropertyMapping(rsw,resultMap,metaObject,null);
        }
        return resultObject;
    }

    private Object createResultObject(ResultSetWrapper rsw,ResultMap resultMap,String columnPrefix) throws SQLException {
        final List<Class<?>> constructorArgTypes = new ArrayList<>();
        final List<Object> constructorArgs = new ArrayList<>();
        return createResultObject(rsw,resultMap,constructorArgTypes,constructorArgs,columnPrefix);
    }

    private Object createResultObject(ResultSetWrapper rsw,ResultMap resultMap,List<Class<?>> constructorArgTypes,List<Object> constructorArgs
            ,String columnPrefix) throws SQLException {
        final Class<?> resultType = resultMap.getType();
        final MetaClass metaType = MetaClass.forClass(resultType);
        if (typeHandlerRegistry.hasTypeHandler(resultType)) {
            return createPrimitiveResultObject(rsw,resultMap,columnPrefix);
        } else if (resultType.isInterface() || metaType.hasDefaultConstructor()) {
            return objectFactory.create(resultType);
        }
        throw new RuntimeException("Do not know how to create an instance of " + resultType);
    }

    private Object createPrimitiveResultObject(ResultSetWrapper rsw,ResultMap resultMap,String columnPrefix) throws SQLException {
        final Class<?> resultType = resultMap.getType();
        final String columnName;
        if (!resultMap.getResultMappings().isEmpty()) {
            final List<ResultMapping> resultMappingList = resultMap.getResultMappings();
            final ResultMapping mapping = resultMappingList.get(0);
            columnName = prependPrefix(mapping.getColumn(),columnPrefix);
        } else {
            columnName = rsw.getColumnNames().get(0);
        }
        final TypeHandler<?> typeHandler = rsw.getTypeHandler(resultType,columnName);
        return typeHandler.getResult(rsw.getResultSet(),columnName);
    }

    private String prependPrefix(String columnName,String prefix) {
        if (columnName == null || columnName.length() == 0 || prefix == null || prefix.length() == 0) {
            return columnName;
        }
        return prefix + columnName;
    }

    private boolean applyAutomaticMappings(ResultSetWrapper rsw,ResultMap resultMap,MetaObject metaObject,String columnPrefix) throws SQLException {
        List<String> unmappedColumnNames = rsw.getUnMappedColumnNames(resultMap, columnPrefix);
        boolean foundValues = false;
        for (String columnName : unmappedColumnNames) {
            String propertyName = columnName;
            if (columnPrefix != null && !columnPrefix.isEmpty()) {
                if (columnName.toUpperCase(Locale.ENGLISH).startsWith(columnPrefix)) {
                    propertyName = columnName.substring(columnPrefix.length());
                } else {
                    continue;
                }
            }
            final String property = metaObject.findProperty(propertyName,false);
            if (property != null && metaObject.hasSetter(property)) {
                final Class<?> propertyType = metaObject.getSetterType(property);
                if (typeHandlerRegistry.hasTypeHandler(propertyType)) {
                    final TypeHandler<?> typeHandler = rsw.getTypeHandler(propertyType, columnName);
                    final Object value = typeHandler.getResult(rsw.getResultSet(), columnName);
                    if (value != null) {
                        foundValues = true;
                    }
                    if (value != null || propertyType.isPrimitive()) {
                        metaObject.setValue(property,value);
                    }
                }
            }
        }
        return foundValues;
    }

    private boolean applyPropertyMapping(ResultSetWrapper rsw,ResultMap resultMap,MetaObject metaObject,String columnPrefix) throws SQLException {
        final List<String> mappedColumnNames = rsw.getMappedColumnNames(resultMap, columnPrefix);
        boolean foundValues = false;
        final List<ResultMapping> propertyMappings = resultMap.getPropertyResultMappings();
        for (ResultMapping propertyMapping : propertyMappings) {
            final String column = propertyMapping.getColumn();
            if (column != null && mappedColumnNames.contains(column.toUpperCase(Locale.ENGLISH))) {
                final TypeHandler<?> typeHandler = propertyMapping.getTypeHandler();
                Object value = typeHandler.getResult(rsw.getResultSet(), column);
                final String property = propertyMapping.getProperty();
                if (value != NO_VALUE && property != null && value != null) {
                    metaObject.setValue(property,value);
                    foundValues = true;
                }
            }
        }
        return foundValues;
    }


}
