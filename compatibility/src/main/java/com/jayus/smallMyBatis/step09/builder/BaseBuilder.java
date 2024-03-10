package com.jayus.smallMyBatis.step09.builder;

import com.jayus.smallMyBatis.step09.session.Configuration;
import com.jayus.smallMyBatis.step09.type.TypeAliasRegistry;
import com.jayus.smallMyBatis.step09.type.TypeHandlerRegistry;

/**
 * 构建器的基类 建造者模式
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    protected final TypeAliasRegistry typeAliasRegistry;

    protected final TypeHandlerRegistry typeHandlerRegistry;


    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = configuration.getTypeAliasRegistry();
        this.typeHandlerRegistry = configuration.getTypeHandlerRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    protected Class<?> resolveAlias(String alias) {
        return typeAliasRegistry.resolveAlias(alias);
    }
}
