package com.jayus.smallMyBatis.step13.mapping;

/**
 * @ClassName SqlCommandType
 * @Description: SQL 指令类型
 * @date: 2024/10/13 09:18
 */
public enum SqlCommandType {

    /**
     * 未知
     */
    UNKNOWN,
    /**
     * 插入
     */
    INSERT,
    /**
     * 更新
     */
    UPDATE,
    /**
     * 删除
     */
    DELETE,
    /**
     * 查找
     */
    SELECT;

}
