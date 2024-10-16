package com.jayus.smallMyBatis.step12.builder;

import com.jayus.smallMyBatis.step12.mapping.MappedStatement;
import com.jayus.smallMyBatis.step12.mapping.ResultMap;
import com.jayus.smallMyBatis.step12.mapping.SqlCommandType;
import com.jayus.smallMyBatis.step12.mapping.SqlSource;
import com.jayus.smallMyBatis.step12.script.LanguageDriver;
import com.jayus.smallMyBatis.step12.session.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MapperBuilderAssistant
 * @Description: 映射构建器助手，建造者
 * @date: 2024/9/26 10:10
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

    public String applyCurrentNamespace(String base, boolean isReference) {
        if (base == null) {
            return null;
        }
        if (isReference) {
            if (base.contains(".")) return base;
        }
        return currentNamespace + "." + base;
    }

    public MappedStatement addMappedStatement(String id, SqlSource sqlSource, SqlCommandType sqlCommandType, Class<?> parameterType
            , String resultMap, Class<?> resultType, LanguageDriver lang) {
        id = applyCurrentNamespace(id, false);
        MappedStatement.Builder statementBuilder = new MappedStatement.Builder(configuration, id, sqlCommandType, sqlSource, resultType);
        setStatementResultMap(resultMap,resultType,statementBuilder);
        MappedStatement statement = statementBuilder.build();
        // 映射语句信息，建造完存放到配置项中
        configuration.addMapperStatement(statement);
        return statement;
    }

    private void setStatementResultMap(String resultMap, Class<?> resultType, MappedStatement.Builder statementBuilder) {
        resultMap = applyCurrentNamespace(resultMap, true);
        List<ResultMap> resultMaps = new ArrayList<>();
        if (resultMap != null) {
            // TODO：暂无Map结果映射配置，本章节不添加此逻辑
        } else if (resultType != null) {
            ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(configuration, statementBuilder.id() + "-Inline", resultType, new ArrayList<>());
            resultMaps.add(inlineResultMapBuilder.build());
        }
        statementBuilder.resultMaps(resultMaps);
    }


}
