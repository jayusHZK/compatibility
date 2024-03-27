package com.jayus.smallMyBatis.step10.type;

import com.jayus.smallMyBatis.step10.session.Configuration;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Long 类型处理器
 */
public class LongTypeHandler extends BaseTypeHandler<Long> {

    @Override
    protected void setNunNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i,parameter);
    }

}
