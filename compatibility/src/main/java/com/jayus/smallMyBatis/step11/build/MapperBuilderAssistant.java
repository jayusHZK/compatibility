package com.jayus.smallMyBatis.step11.build;

import com.jayus.smallMyBatis.step11.mapping.MappedStatement;
import com.jayus.smallMyBatis.step11.mapping.ResultMap;
import com.jayus.smallMyBatis.step11.mapping.SqlCommandType;
import com.jayus.smallMyBatis.step11.mapping.SqlSource;
import com.jayus.smallMyBatis.step11.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step11.session.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MapperBuilderAssistant
 * @Description: 映射构建器助手，建造者
 * @date: 2024/9/18 21:43
 */
public class MapperBuilderAssistant extends BaseBuilder {

    private String currentNamespace;

    private String resource;

    public MapperBuilderAssistant(Configuration configuration, String resource) {
        super(configuration);
        this.resource = resource;
    }

    public String getCurrentNamespace() {
        return currentNamespace;
    }

    public void setCurrentNamespace(String currentNamespace) {
        this.currentNamespace = currentNamespace;
    }

    public String applyCurrentNameSpace(String base, boolean isReference) {
        if (base == null) {
            return null;
        }
        if (isReference) {
            if (base.contains(".")) return base;
        }
        return currentNamespace + "." + base;
    }

    public MappedStatement addMappedStatement(String id, SqlSource sqlSource, SqlCommandType sqlCommandType
            , Class<?> parameterType, String resultMap, Class<?> resultType, LanguageDriver lang) {
        // 给id加上namespace前缀：cn.bugstack.mybatis.test.dao.IUserDao.queryUserInfoById
        id = applyCurrentNameSpace(id, false);
        MappedStatement.Builder statementBuilder = new MappedStatement.Builder(configuration
                , id, sqlCommandType, sqlSource, resultType);
        // 结果映射，给 MappedStatement#resultMaps
        setStatementResultMap(resultMap, resultType, statementBuilder);
        MappedStatement statement = statementBuilder.build();
        // 映射语句信息，建造完存放到配置项中
        configuration.addMappedStatement(statement);
        return statement;
    }

    private void setStatementResultMap(String resultMap, Class<?> resultType
            , MappedStatement.Builder statementBuilder) {
        // 因为暂时还没有在 Mapper XML 中配置 Map 返回结果，所以这里返回的是 null
        resultMap = applyCurrentNameSpace(resultMap, true);
        List<ResultMap> resultMaps = new ArrayList<>();
        if (resultMap != null) {
            // TODO：暂无Map结果映射配置，本章节不添加此逻辑
        }
        /*
         * 通常使用 resultType 即可满足大部分场景
         * <select id="queryUserInfoById" resultType="cn.bugstack.mybatis.test.po.User">
         * 使用 resultType 的情况下，Mybatis 会自动创建一个 ResultMap，基于属性名称映射列到 JavaBean 的属性上。
         */
        else if (resultType != null) {
            ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(configuration
                    , statementBuilder.id() + "-Inline", resultType, new ArrayList<>());
            resultMaps.add(inlineResultMapBuilder.build());
        }
        statementBuilder.resultMaps(resultMaps);

    }
}