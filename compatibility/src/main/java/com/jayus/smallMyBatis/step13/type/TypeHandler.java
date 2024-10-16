package com.jayus.smallMyBatis.step13.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName TypeHandler
 * @Description: 类型处理器
 * @date: 2024/10/10 09:07
 */
public interface TypeHandler<T> {

    void setParameter(PreparedStatement ps,int i,T parameter,JdbcType jdbcType) throws SQLException;

    T getResult(ResultSet rs, String columnName) throws SQLException;

}
