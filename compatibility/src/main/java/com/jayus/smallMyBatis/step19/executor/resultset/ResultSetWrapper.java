package com.jayus.smallMyBatis.step19.executor.resultset;

import com.jayus.smallMyBatis.step19.io.Resources;
import com.jayus.smallMyBatis.step19.mapping.ResultMap;
import com.jayus.smallMyBatis.step19.session.Configuration;
import com.jayus.smallMyBatis.step19.type.JdbcType;
import com.jayus.smallMyBatis.step19.type.TypeHandler;
import com.jayus.smallMyBatis.step19.type.TypeHandlerRegistry;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * @ClassName ResultSetWrapper
 * @Description: 结果集包装器
 * @date: 2024/10/18 19:43
 */
public class ResultSetWrapper {

    private final ResultSet resultSet;

    private final TypeHandlerRegistry typeHandlerRegistry;

    private final List<String> columnNames = new ArrayList<>();

    private final List<String> classNames = new ArrayList<>();

    private final List<JdbcType> jdbcTypes = new ArrayList<>();

    private final Map<String, Map<Class<?>, TypeHandler<?>>> typeHandlerMap = new HashMap<>();

    private Map<String, List<String>> mappedColumnNamesMap = new HashMap<>();

    private Map<String, List<String>> unMappedColumnNamesMap = new HashMap<>();

    public ResultSetWrapper(ResultSet rs, Configuration configuration) throws SQLException {
        super();
        this.typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        this.resultSet = rs;
        final ResultSetMetaData metaData = rs.getMetaData();
        final int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnLabel(i));
            jdbcTypes.add(JdbcType.forCode(metaData.getColumnType(i)));
            classNames.add(metaData.getColumnClassName(i));
        }
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<String> getClassNames() {
        return Collections.unmodifiableList(classNames);
    }

    public TypeHandler<?> getTypeHandler(Class<?> propertyType, String columnName) {
        TypeHandler<?> handler = null;
        Map<Class<?>, TypeHandler<?>> columnHandlers = typeHandlerMap.get(columnName);
        if (columnHandlers == null) {
            columnHandlers = new HashMap<>();
            typeHandlerMap.put(columnName, columnHandlers);
        } else {
            handler = columnHandlers.get(propertyType);
        }
        if (handler == null) {
            handler = typeHandlerRegistry.getTypeHandler(propertyType, null);
            columnHandlers.put(propertyType, handler);
        }
        return handler;
    }

    public Class<?> resolveClass(String className) {
        try {
            return Resources.classForName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private void loadMappedAndUnMappedColumnNames(ResultMap resultMap, String columnPrefix) {
        List<String> mappedColumnNames = new ArrayList<>();
        List<String> unMappedColumnNames = new ArrayList<>();
        final String upperColumnPrefix = columnPrefix == null ? null : columnPrefix.toUpperCase(Locale.ENGLISH);
        final Set<String> mappedColumns = prependPrefixes(resultMap.getMappedColumns(), upperColumnPrefix);
        for (String columnName : columnNames) {
            final String upperColumnName = columnName.toUpperCase(Locale.ENGLISH);
            if (mappedColumns.contains(upperColumnName)) {
                mappedColumnNames.add(upperColumnName);
            } else {
                unMappedColumnNames.add(columnName);
            }
        }
        mappedColumnNamesMap.put(getMapKey(resultMap,columnPrefix),mappedColumnNames);
        unMappedColumnNamesMap.put(getMapKey(resultMap,columnPrefix),unMappedColumnNames);
    }

    public List<String> getMappedColumnNames(ResultMap resultMap,String columnPrefix) {
        List<String> mappedColumnNames = mappedColumnNamesMap.get(getMapKey(resultMap, columnPrefix));
        if (mappedColumnNames == null) {
            loadMappedAndUnMappedColumnNames(resultMap,columnPrefix);
            mappedColumnNames = mappedColumnNamesMap.get(getMapKey(resultMap,columnPrefix));
        }
        return mappedColumnNames;
    }

    public List<String> getUnMappedColumnNames(ResultMap resultMap,String columnPrefix) {
        List<String> unMappedColumnNames = unMappedColumnNamesMap.get(getMapKey(resultMap, columnPrefix));
        if (unMappedColumnNames == null) {
            loadMappedAndUnMappedColumnNames(resultMap,columnPrefix);
            unMappedColumnNames = unMappedColumnNamesMap.get(getMapKey(resultMap,columnPrefix));
        }
        return unMappedColumnNames;
    }

    private String getMapKey(ResultMap resultMap, String columnPrefix) {
        return resultMap.getId() + ":" + columnPrefix;
    }

    private Set<String> prependPrefixes(Set<String> columnNames, String prefix) {
        if (columnNames == null || columnNames.isEmpty() || prefix == null || prefix.length() == 0) {
            return columnNames;
        }
        final Set<String> prefixed = new HashSet<>();
        for (String columnName : columnNames) {
            prefixed.add(prefix + columnName);
        }
        return prefixed;
    }

}
