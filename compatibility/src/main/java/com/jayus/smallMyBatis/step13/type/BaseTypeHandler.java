package com.jayus.smallMyBatis.step13.type;

import com.jayus.smallMyBatis.step13.session.Configuration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName BaseTypeHandler
 * @Description: 类型处理器的基类
 * @date: 2024/10/10 18:05
 */
public abstract class BaseTypeHandler<T> implements TypeHandler<T> {

    protected Configuration configuraction;

    public void setConfiguraction(Configuration configuraction) {
        this.configuraction = configuraction;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        setNonNullResult(ps,i,parameter,jdbcType);
    }

    @Override
    public T getResult(ResultSet rs, String columnName) throws SQLException {
        return getNullableResult(rs,columnName);
    }

    protected abstract void setNonNullResult(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;

    protected abstract T getNullableResult(ResultSet rs, String columnName) throws SQLException;

}
