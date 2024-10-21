package com.jayus.smallMyBatis.step19.builder;

import com.jayus.smallMyBatis.step19.session.Configuration;
import com.jayus.smallMyBatis.step19.type.TypeAliasRegistry;
import com.jayus.smallMyBatis.step19.type.TypeHandler;
import com.jayus.smallMyBatis.step19.type.TypeHandlerRegistry;

/**
 * @ClassName BaseBuilder
 * @Description: 构建器的基类，建造者模式
 * @date: 2024/10/19 13:41
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    protected final TypeAliasRegistry typeAliasRegistry;
    protected final TypeHandlerRegistry typeHandlerRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
        this.typeHandlerRegistry = this.configuration.getTypeHandlerRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    protected Class<?> resolveAlias(String alias) {
        return typeAliasRegistry.resolveAlias(alias);
    }

    protected Class<?> resolveClass(String alias) {
        if (alias == null) {
            return null;
        }
        try {
            return resolveAlias(alias);
        } catch (Exception e) {
            throw new RuntimeException("Error resolving class. Cause: " + e, e);
        }
    }

    protected TypeHandler<?> resolveTypeHandler(Class<?> javaType,Class<? extends TypeHandler<?>> typeHandlerType) {
        if (typeHandlerType == null) {
            return null;
        }
        return typeHandlerRegistry.getMappingTypeHandler(typeHandlerType);
    }

    protected Boolean booleanValueOf(String value,Boolean defaultValue) {
        return value == null ? defaultValue : Boolean.valueOf(value);
    }

}
