package com.jayus.smallMyBatis.step12.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName ParameterHandler
 * @Description: 参数处理器
 * @date: 2024/9/25 09:28
 */
public interface ParameterHandler {

    /*
    获取参数
     */
    Object getParameterObject();

    /*
    设置参数
     */
    void setParameters(PreparedStatement ps) throws SQLException;

}
