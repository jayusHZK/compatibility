package com.jayus.smallMyBatis.step09.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 类型处理器
 * @param <T>
 */
public interface TypeHandler<T> {

    void setParamenter(PreparedStatement ps,int i,T parameter,JdbcType jdbcType) throws SQLException;

}
