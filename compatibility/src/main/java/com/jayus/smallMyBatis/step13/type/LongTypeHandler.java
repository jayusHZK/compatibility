package com.jayus.smallMyBatis.step13.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName LongTypeHandler
 * @Description: Long 类型处理器
 * @date: 2024/10/10 18:09
 */
public class LongTypeHandler extends BaseTypeHandler<Long> {

    @Override
    protected void setNonNullResult(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i,parameter);
    }

    @Override
    protected Long getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getLong(columnName);
    }
}
