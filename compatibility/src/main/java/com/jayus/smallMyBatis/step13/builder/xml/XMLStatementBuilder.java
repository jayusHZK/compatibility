package com.jayus.smallMyBatis.step13.builder.xml;

import com.jayus.smallMyBatis.step13.builder.BaseBuilder;
import com.jayus.smallMyBatis.step13.builder.MapperBuilderAssistant;
import com.jayus.smallMyBatis.step13.mapping.SqlCommandType;
import com.jayus.smallMyBatis.step13.mapping.SqlSource;
import com.jayus.smallMyBatis.step13.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step13.session.Configuration;
import org.dom4j.Element;

import java.util.Locale;

/**
 * @ClassName XMLStatementBuilder
 * @Description: XML语句构建器
 * @date: 2024/10/16 11:40
 */
public class XMLStatementBuilder extends BaseBuilder {

    private MapperBuilderAssistant builderAssistant;

    private Element element;

    public XMLStatementBuilder(Configuration configuration, MapperBuilderAssistant builderAssistant, Element element) {
        super(configuration);
        this.builderAssistant = builderAssistant;
        this.element = element;
    }

    public void parseStatementNode(){
        String id = element.attributeValue("id");
        String parameterType = element.attributeValue("parameterType");
        Class<?> parameterTypeClass = resolveAlias(parameterType);
        String resultMap = element.attributeValue("resultMap");
        String resultType = element.attributeValue("resultType");
        Class<?> resultTypeClass = resolveAlias(resultType);
        String nodeName = element.getName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));

        Class<?> langClass = configuration.getLanguageRegistry().getDefaultDriverClass();
        LanguageDriver langDriver = configuration.getLanguageRegistry().getDriver(langClass);
        // 解析成SqlSource，DynamicSqlSource/RawSqlSource
        SqlSource sqlSource = langDriver.createSqlSource(configuration, element, parameterTypeClass);

        builderAssistant.addMapperStatement(id,sqlSource,sqlCommandType,parameterTypeClass,resultMap,resultTypeClass,langDriver);
    }

}
