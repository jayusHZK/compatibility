package com.jayus.smallMyBatis.step04.type;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * JDBC 类型
 */
public enum JdbcType {

    INTEGER(Types.INTEGER),
    FLOAT(Types.FLOAT),
    DOUBLE(Types.DOUBLE),
    DECIMAL(Types.DECIMAL),
    VARCHAR(Types.VARCHAR),
    TIMESTAMP(Types.TIMESTAMP)
    ;

    /**
     * 类型编码
     */
    public final int TYPE_CODE;

    /**
     * 用于存放类型编码和枚举型的映射关系
     */
    private static Map<Integer,JdbcType> codeLookup = new HashMap<>();

    static{
        for (JdbcType type : JdbcType.values()) {
            codeLookup.put(type.TYPE_CODE,type);
        }
    }

    JdbcType(int TYPE_CODE) {
        this.TYPE_CODE = TYPE_CODE;
    }

    public static JdbcType forCode(int code){
        return codeLookup.get(code);
    }
}
