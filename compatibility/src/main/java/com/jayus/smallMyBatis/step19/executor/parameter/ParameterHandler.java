package com.jayus.smallMyBatis.step19.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName ParameterHandler
 * @Description: 参数处理器
 * @date: 2024/10/18 11:43
 */
public interface ParameterHandler {

    Object getParameterObject();

    void setParameters(PreparedStatement ps) throws SQLException;

}
