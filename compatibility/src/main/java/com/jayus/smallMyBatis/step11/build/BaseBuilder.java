package com.jayus.smallMyBatis.step11.build;

import com.jayus.smallMyBatis.step11.session.Configuration;
import com.jayus.smallMyBatis.step11.type.TypeAliasRegistry;
import com.jayus.smallMyBatis.step11.type.TypeHandlerRegistry;

/**
 * @ClassName BaseBuilder
 * @Description: 构建器的基类，建造者模式
 * @date: 2024/9/17 22:48
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
}
