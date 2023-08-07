package com.jayus.smallSpring.step18.jdbc.core;

import com.jayus.smallSpring.step18.jdbc.IncorrectResultSetColumnCountException;
import com.jayus.smallSpring.step18.jdbc.support.JdbcUtils;
import com.jayus.smallSpring.step18.util.NumberUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SingletColumnRowMapper<T> implements RowMapper<T> {

    private Class<?> requireType;

    public SingletColumnRowMapper() {
    }

    public SingletColumnRowMapper(Class<?> requireType) {
        this.requireType = requireType;
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int columnCount = rsMetaData.getColumnCount();
        if (columnCount != 1){
            throw new IncorrectResultSetColumnCountException(1,columnCount);
        }
        Object result = getColumnValue(rs, 1, this.requireType);
        if (result != null && this.requireType != null && !this.requireType.isInstance(result)){
            try {
                return (T) convertValueToRequiredType(result,this.requireType);
            } catch (IllegalArgumentException e) {
                //throw new RuntimeException(e);
            }
        }
        return (T) result;
    }

    protected Object getColumnValue(ResultSet rs,int index,Class<?> requireType) throws SQLException {
        if (null != requireType){
            return JdbcUtils.getResultSetValue(rs,index,requireType);
        } else {
            return getColumnValue(rs,index);
        }
    }

    protected Object getColumnValue(ResultSet rs,int index) throws SQLException {
        return JdbcUtils.getResultSetValue(rs,index);
    }

    protected Object convertValueToRequiredType(Object value,Class<?> requiredType){
        if (String.class == requiredType){
            return value.toString();
        } else if (Number.class.isAssignableFrom(requiredType)){
            if (value instanceof Number){
                return NumberUtils.convertNumberToTargetClass((Number) value,(Class<Number>) requiredType);
            } else {
                return NumberUtils.parseNumber(value.toString(),(Class<Number>) requiredType);
            }
        } else {
            throw new IllegalArgumentException(
                    "Value [" + value + "] is of type [" + value.getClass().getName() +
                            "] and cannot be converted to required type [" + requiredType.getName() + "]");
        }
    }

}
