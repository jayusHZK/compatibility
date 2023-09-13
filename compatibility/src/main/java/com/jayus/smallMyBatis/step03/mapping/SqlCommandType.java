package com.jayus.smallMyBatis.step03.mapping;

/**
 * SQL 命令类型
 */
public enum SqlCommandType {

    // 未知
    UNKNOWN,
    // 插入
    INSERT,
    // 修改
    UPDATE,
    // 删除
    DELETE,
    // 查找
    SELECT;

}
