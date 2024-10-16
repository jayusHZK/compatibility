package com.jayus.smallMyBatis.step13.builder;

import com.jayus.smallMyBatis.step13.mapping.*;
import com.jayus.smallMyBatis.step13.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step13.session.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MapperBuilderAssistant
 * @Description: 映射构建器助手 ，建造者
 * @date: 2024/10/14 22:15
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

    public void setCurrentNameSpace(String currentNamespace) {
        this.currentNamespace = currentNamespace;
    }

    public String applyCurrentNamespace(String base, boolean isReference) {
        if (base == null) {
            return null;
        }
        if (isReference) {
            if (base.contains(".")) return base;
        } else {
            if (base.startsWith(currentNamespace + ".")) {
                return base;
            }
            if (base.contains(".")) {
                throw new RuntimeException("Dots are not allowed in element names, please remove it from " + base);
            }
        }
        return currentNamespace + "." + base;
    }

    public MappedStatement addMapperStatement(String id, SqlSource sqlSource, SqlCommandType sqlCommandType, Class<?> parameterType
            , String resultMap, Class<?> resultType, LanguageDriver lang) {
        id = applyCurrentNamespace(id,false);
        MappedStatement.Builder statementBuilder = new MappedStatement.Builder(configuration, id, sqlCommandType, sqlSource, resultType);
        setStatementResultMap(resultMap,resultType,statementBuilder);
        MappedStatement statement = statementBuilder.build();
        configuration.addMappedStatement(statement);
        return statement;
    }

    private void setStatementResultMap(String resultMap,Class<?> resultType,MappedStatement.Builder statementBuilder ){
        resultMap = applyCurrentNamespace(resultMap,true);
        List<ResultMap> resultMaps = new ArrayList<>();
        if (resultMap != null) {
            String[] resultMapNames = resultMap.split(",");
            for (String resultMapName : resultMapNames) {
                resultMaps.add(configuration.getResultMap(resultMapName.trim()));
            }
        }
         else if (resultType != null) {
            ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(configuration, statementBuilder.id() + "-Inline"
                    , resultType, new ArrayList<>());
            resultMaps.add(inlineResultMapBuilder.build());
        }
         statementBuilder.resultMaps(resultMaps);
    }

    public ResultMap addResultMap(String id, Class<?> type, List<ResultMapping> resultMappings) {
        ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(configuration, id, type, resultMappings);
        ResultMap resultMap = inlineResultMapBuilder.build();
        configuration.addResultMap(resultMap);
        return resultMap;
    }

}
