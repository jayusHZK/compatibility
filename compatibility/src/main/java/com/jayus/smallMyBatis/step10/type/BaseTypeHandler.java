package com.jayus.smallMyBatis.step10.type;

import com.jayus.smallMyBatis.step10.session.Configuration;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 类型处理器的基类
 */
public abstract class BaseTypeHandler<T> implements TypeHandler<T> {

    protected Configuration configuration;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        // 定义抽象方法 由子对象实现不同类型的属性设置
        setNunNullParameter(ps,i,parameter,jdbcType);
    }

    protected abstract void setNunNullParameter(PreparedStatement ps,int i,T parameter,JdbcType jdbcType) throws SQLException;
}
