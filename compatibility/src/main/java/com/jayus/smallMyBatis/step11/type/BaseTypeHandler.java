package com.jayus.smallMyBatis.step11.type;

import com.jayus.smallMyBatis.step11.session.Configuration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName BaseTypeHandler
 * @Description: 类型处理器的基类
 * @date: 2024/5/13 16:44
 */
public abstract class BaseTypeHandler<T> implements TypeHandler<T> {

    protected Configuration configuration;

    public void setConfiguration(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        // 定义抽象方法，由子类实现不同类型的属性设置
        setNonNullParameter(ps,i,parameter,jdbcType);
    }

    @Override
    public T getResult(ResultSet rs, String columnName) throws SQLException {
        return getNullableResult(rs,columnName);
    }

    protected abstract void setNonNullParameter(PreparedStatement ps,int i,T parameter,JdbcType jdbcType) throws SQLException;

    protected abstract T getNullableResult(ResultSet rs,String columnName) throws SQLException;

}
