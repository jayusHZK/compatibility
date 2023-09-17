package com.jayus.smallMyBatis.step05.type;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public enum JdbcType {
    /**
     * 整数类型
     */
    INTEGER(Types.INTEGER),
    /**
     * float类型
     */
    FLOAT(Types.FLOAT),
    /**
     * double类型
     */
    DOUBLE(Types.DOUBLE),
    /**
     * decimal类型
     */
    DECIMAL(Types.DECIMAL),
    /**
     * 可变长字符串类型
     */
    VARCHAR(Types.VARCHAR),
    /**
     * 时间戳类型
     */
    TIMESTAMP(Types.TIMESTAMP);

    public final int TYPE_CODE;

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
