package com.jayus.smallMyBatis.step08.builder;

import com.jayus.smallMyBatis.step08.session.Configuration;
import com.jayus.smallMyBatis.step08.type.TypeAliasRegistry;
import com.jayus.smallMyBatis.step08.type.TypeHandlerRegistry;

/**
 * 构建器的基类 建造者模式
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

    protected Class<?> resolveAlias(String alias){
        return typeAliasRegistry.resolveAlias(alias);
    }
}
