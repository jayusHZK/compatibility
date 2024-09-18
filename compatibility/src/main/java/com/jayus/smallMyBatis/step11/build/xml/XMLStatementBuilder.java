package com.jayus.smallMyBatis.step11.build.xml;

import com.jayus.smallMyBatis.step11.build.BaseBuilder;
import com.jayus.smallMyBatis.step11.build.MapperBuilderAssistant;
import com.jayus.smallMyBatis.step11.mapping.SqlCommandType;
import com.jayus.smallMyBatis.step11.mapping.SqlSource;
import com.jayus.smallMyBatis.step11.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step11.session.Configuration;
import org.dom4j.Element;

import java.util.Locale;

/**
 * @ClassName XMLStatementBuilder
 * @Description: XML 语句构建器
 * @date: 2024/9/18 22:29
 */
public class XMLStatementBuilder extends BaseBuilder {

    private MapperBuilderAssistant builderAssistant;

    private Element element;

    public XMLStatementBuilder(Configuration configuration, MapperBuilderAssistant builderAssistant, Element element) {
        super(configuration);
        this.builderAssistant = builderAssistant;
        this.element = element;
    }

    //解析语句(select|insert|update|delete)
    public void parseStatementNode() {
        String id = element.attributeValue("id");
        // 参数类型
        String parameterType = element.attributeValue("parameterType");
        Class<?> parameterTypeClass = resolveAlias(parameterType);
        // 外部应用 resultMap
        String resultMap = element.attributeValue("resultMap");
        // 结果类型
        String resultType = element.attributeValue("resultType");
        Class<?> resultTypeClass = resolveAlias(resultType);
        String nodeName = element.getName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
        // 获取默认语言驱动器
        Class<?> langClass = configuration.getLanguageRegistry().getDefaultDriverClass();
        LanguageDriver langDriver = configuration.getLanguageRegistry().getDriver(langClass);

        // 解析成SqlSource，DynamicSqlSource/RawSqlSource
        SqlSource sqlSource = langDriver.createSqlSource(configuration, element, parameterTypeClass);

        // 调用助手类【本节新添加，便于统一处理参数的包装】
        builderAssistant.addMappedStatement(id, sqlSource, sqlCommandType, parameterTypeClass
                , resultMap, resultTypeClass, langDriver);
    }
}
