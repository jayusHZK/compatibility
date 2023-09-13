package com.jayus.smallMyBatis.step04.builder;

import com.jayus.smallMyBatis.step04.session.Configuration;

public abstract class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
