package com.jayus.smallMyBatis.step11.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName TypeHandler
 * @Description: 类型处理器
 * @date: 2024/5/13 16:42
 */
public interface TypeHandler<T> {

    /**
     * 设置参数
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    void setParameter(PreparedStatement ps ,int i,T parameter,JdbcType jdbcType) throws SQLException;

    /**
     * 获取结果
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    T getResult(ResultSet rs,String columnName) throws SQLException;

}
