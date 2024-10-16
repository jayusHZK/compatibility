package com.jayus.smallMyBatis.step19.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName LongTypeHandler
 * @Description: Long 类型处理器
 * @date: 2024/10/16 20:49
 */
public class LongTypeHandler<T> extends BaseTypeHandler<Long> {

    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i,parameter);
    }

    @Override
    protected Long getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getLong(columnName);
    }

    @Override
    protected Long getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getLong(columnIndex);
    }
}
