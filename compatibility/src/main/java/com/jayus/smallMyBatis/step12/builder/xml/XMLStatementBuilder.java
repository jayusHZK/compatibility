package com.jayus.smallMyBatis.step12.builder.xml;

import com.jayus.smallMyBatis.step12.builder.BaseBuilder;
import com.jayus.smallMyBatis.step12.builder.MapperBuilderAssistant;
import com.jayus.smallMyBatis.step12.mapping.SqlCommandType;
import com.jayus.smallMyBatis.step12.mapping.SqlSource;
import com.jayus.smallMyBatis.step12.script.LanguageDriver;
import com.jayus.smallMyBatis.step12.session.Configuration;
import org.dom4j.Element;

import java.util.Locale;

/**
 * @ClassName XMLStatementBuilder
 * @Description: XML 语句构建器
 * @date: 2024/9/26 15:10
 */
public class XMLStatementBuilder extends BaseBuilder {

    private MapperBuilderAssistant builderAssistant;

    private Element element;

    public XMLStatementBuilder(Configuration configuration, MapperBuilderAssistant builderAssistant, Element element) {
        super(configuration);
        this.builderAssistant = builderAssistant;
        this.element = element;
    }

    public void parseStatementNode() {
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
        SqlSource sqlSource = langDriver.createSqlSource(configuration, element, parameterTypeClass);

        builderAssistant.addMappedStatement(id,sqlSource,sqlCommandType,parameterTypeClass,resultMap,resultTypeClass,langDriver);
    }

}
