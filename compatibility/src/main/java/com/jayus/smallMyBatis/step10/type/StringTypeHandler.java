package com.jayus.smallMyBatis.step10.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * string 类型处理器
 */
public class StringTypeHandler extends BaseTypeHandler<String>{

    @Override
    protected void setNunNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter);
    }
}
