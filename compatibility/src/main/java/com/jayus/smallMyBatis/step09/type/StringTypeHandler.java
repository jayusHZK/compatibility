package com.jayus.smallMyBatis.step09.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * String 类型处理器
 */
public class StringTypeHandler extends BaseTypeHandler<String> {

    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter);
    }
}
