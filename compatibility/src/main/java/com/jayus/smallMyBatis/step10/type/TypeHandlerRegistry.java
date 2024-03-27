package com.jayus.smallMyBatis.step10.type;

import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * 类型处理器注册机
 */
public final class TypeHandlerRegistry {

    private final Map<JdbcType,TypeHandler<?>> JDBC_TYPE_HANDLER_MAP = new EnumMap<>(JdbcType.class);

    private final Map<Type,Map<JdbcType,TypeHandler<?>>> TYPE_HANDLER_MAP = new HashMap<>();

    private final Map<Class<?>,TypeHandler<?>> ALL_TYPE_HANDLERS_MAP = new HashMap<>();

    public TypeHandlerRegistry() {
        register(Long.class,new LongTypeHandler());
        register(long.class,new LongTypeHandler());

        register(String.class,new StringTypeHandler());
        register(String.class,JdbcType.CHAR,new StringTypeHandler());
        register(String.class,JdbcType.VARCHAR,new StringTypeHandler());
    }

    private <T> void register(Type javaType,TypeHandler<? extends T> typeHandler){
        register(javaType,null,typeHandler);
    }

    private void register(Type javaType,JdbcType jdbcType,TypeHandler<?> handler){
        if (null != javaType){
            Map<JdbcType, TypeHandler<?>> map = TYPE_HANDLER_MAP.computeIfAbsent(javaType, k -> new HashMap<>());
            map.put(jdbcType,handler);
        }
        ALL_TYPE_HANDLERS_MAP.put(handler.getClass(),handler);
    }
}
