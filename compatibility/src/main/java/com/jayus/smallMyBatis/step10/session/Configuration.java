package com.jayus.smallMyBatis.step10.session;

import com.jayus.smallMyBatis.step10.binding.MapperRegistry;
import com.jayus.smallMyBatis.step10.mapping.Environment;
import com.jayus.smallMyBatis.step10.scripting.LanguageDriverRegistry;
import com.jayus.smallMyBatis.step10.type.TypeAliasRegistry;
import com.jayus.smallMyBatis.step10.type.TypeHandlerRegistry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 配置项
 */
public class Configuration {

    // 环境
    protected Environment environment;

    // 映射注册机
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    // 映射的语句，存在Map里
    protected final Map<String,MappedStatement> mappedStatements = new HashMap<>();

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    protected final LanguageDriverRegistry languageDriverRegistry = new LanguageDriverRegistry();

    // 类型处理器注册机
    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

    protected final Set<String> loadedResources = new HashSet<>();

    protected String databaseId;

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC",jdbctr);
    }
}
