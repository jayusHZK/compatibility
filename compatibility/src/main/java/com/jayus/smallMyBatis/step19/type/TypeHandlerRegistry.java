package com.jayus.smallMyBatis.step19.type;

import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TypeHandlerRegistry
 * @Description: 类型处理器注册机
 * @date: 2024/10/16 20:54
 */
public final class TypeHandlerRegistry {

    private final Map<JdbcType,TypeHandler<?>> JDBC_TYPE_HANDLER_MAP = new EnumMap<>(JdbcType.class);

    private final Map<Type,Map<JdbcType,TypeHandler<?>>> TYPE_HANDLER_MAP = new HashMap<>();

    private final Map<Class<?>,TypeHandler<?>> ALL_TYPE_HANDLER_MAP = new HashMap<>();



}
