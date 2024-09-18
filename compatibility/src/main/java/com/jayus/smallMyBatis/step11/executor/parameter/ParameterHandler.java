package com.jayus.smallMyBatis.step11.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName ParameterHandler
 * @Description: 参数处理器
 * @date: 2024/5/16 01:29
 */
public interface ParameterHandler {

    /**
     * 获取参数
     * @return
     */
    Object getParameterObject();

    /**
     * 设置参数
     * @param ps
     * @throws SQLException
     */
    void setParameters(PreparedStatement ps) throws SQLException;

}
