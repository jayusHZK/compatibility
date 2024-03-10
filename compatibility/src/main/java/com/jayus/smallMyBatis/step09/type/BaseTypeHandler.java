package com.jayus.smallMyBatis.step09.type;

import com.jayus.smallMyBatis.step09.session.Configuration;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 类型处理器的基类
 * @param <T>
 */
public abstract class BaseTypeHandler<T> implements TypeHandler<T> {

    protected Configuration configuration;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void setParamenter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        setNonNullParameter(ps,i,parameter,jdbcType);
    }

    protected abstract void setNonNullParameter(PreparedStatement ps,int i,T parameter,JdbcType jdbcType) throws SQLException;
}
