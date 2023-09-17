package com.jayus.smallMyBatis.step07.builder;

import com.jayus.smallMyBatis.step07.session.Configuration;

public abstract class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
