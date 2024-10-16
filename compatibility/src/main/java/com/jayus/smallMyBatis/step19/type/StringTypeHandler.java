package com.jayus.smallMyBatis.step19.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName StringTypeHandler
 * @Description: String 类型处理器
 * @date: 2024/10/16 20:51
 */
public class StringTypeHandler<T> extends BaseTypeHandler<String> {

    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter);
    }

    @Override
    protected String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    protected String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }
}
