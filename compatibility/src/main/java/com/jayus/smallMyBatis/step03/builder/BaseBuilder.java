package com.jayus.smallMyBatis.step03.builder;

import com.jayus.smallMyBatis.step03.session.Configuration;

/**
 * 基础建造器
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
